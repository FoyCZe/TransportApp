package com.company.transportapp.controller;

import com.company.transportapp.dto.AuthRequestDTO;
import com.company.transportapp.dto.AuthResponseDTO;
import com.company.transportapp.dto.MeResponseDTO;
import com.company.transportapp.model.entities.User;
import com.company.transportapp.repository.UserRepository;
import com.company.transportapp.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;
    private final UserRepository userRepository;



    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request,
                                                 HttpServletResponse response) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalStateException("Uživatel nenalezen"));

        String accesstoken = jwtProvider.generateToken(
                user.getUsername(),
                user.getRole()
        );

        String refreshToken = jwtProvider.generateRefreshToken(user.getUsername());

        // HttpOnly cookie
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)  // v produkci TRUE (HTTPS)
                .path("/api/auth/refresh")
                .maxAge(60 * 60 * 24 *7)  // 7 dní
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return ResponseEntity.ok(
                new AuthResponseDTO(
                    accesstoken,
                    user.getUsername(),
                    user.getRole()
                )
        );
    }

    // REFRESH
    @PostMapping("/refresh")
    public AuthResponseDTO refresh(
            @CookieValue("refreshToken") String refreshToken
    ) {
        if (!jwtProvider.validateRefreshToken(refreshToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Neplatný refresh token");
        }

        String username = jwtProvider.getUsernameFromRefresh(refreshToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Uživatel nenalezen"));

        String newAccessToken = jwtProvider.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return new AuthResponseDTO(
                newAccessToken,
                user.getUsername(),
                user.getRole()
        );
    }

    // AKTUÁLNÍ UŽIVATEL
    @GetMapping("/me")
    public MeResponseDTO me(Authentication authentication) {
        String username = authentication.getName();  // SPZ

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Uživatel nenalezen"));

        return new MeResponseDTO(
                user.getUsername(),
                user.getRole(),
                user.isEnabled()
        );
    }

    // LOGOUT
    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/api/auth/refresh")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
    }
}
