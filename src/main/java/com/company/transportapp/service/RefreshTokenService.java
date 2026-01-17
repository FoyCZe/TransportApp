package com.company.transportapp.service;

import com.company.transportapp.model.entities.RefreshToken;
import com.company.transportapp.model.entities.User;
import com.company.transportapp.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repo;

    public RefreshToken create(User user) {
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(Instant.now().plusSeconds(60 * 60 * 24 * 14));
        token.setRevoked(false);

        return repo.save(token);
    }

    public RefreshToken verify(String token) {
        RefreshToken rt = repo.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token nenalezen"));

        if (rt.isRevoked() || rt.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Refresh token neplatný");
        }

        return rt;
    }

    public void revokeAll(User user) {
        repo.deleteByUserId(user.getId());
    }
}
