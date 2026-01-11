package com.company.transportapp.controller;

import com.company.transportapp.dto.AuthRequestDTO;
import com.company.transportapp.dto.AuthResponseDTO;
import com.company.transportapp.dto.MeResponseDTO;
import com.company.transportapp.model.entities.User;
import com.company.transportapp.repository.UserRepository;
import com.company.transportapp.security.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public AuthResponseDTO login(@Valid @RequestBody AuthRequestDTO request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalStateException("Uživatel nenalezen"));

        String token = jwtProvider.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return new AuthResponseDTO(
                token,
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
}
