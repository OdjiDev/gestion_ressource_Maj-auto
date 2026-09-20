package com.odji.spring_back_end.auth.service;

import com.odji.spring_back_end.auth.entity.RefreshToken;
import com.odji.spring_back_end.auth.repository.RefreshTokenRepository;
import com.odji.spring_back_end.common.exception.BusinessException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Value("${app.jwt.refresh-expiration-ms:604800000}")
    private long refreshExpirationMs;

    @Transactional
    public RefreshToken create(User user) {
        RefreshToken token = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(Instant.now().plusMillis(refreshExpirationMs))
                .revoked(false)
                .build();
        return repository.save(token);
    }

    @Transactional(readOnly = true)
    public RefreshToken verify(String tokenStr) {
        RefreshToken token = repository.findByToken(tokenStr)
                .orElseThrow(() -> new ResourceNotFoundException("RefreshToken", tokenStr));
        if (token.isRevoked()) throw new BusinessException("Refresh token révoqué");
        if (token.getExpiryDate().isBefore(Instant.now())) {
            repository.delete(token);
            throw new BusinessException("Refresh token expiré");
        }
        return token;
    }

    @Transactional
    public RefreshToken rotate(RefreshToken oldToken) {
        oldToken.setRevoked(true);
        repository.save(oldToken);
        return create(oldToken.getUser());
    }

    @Transactional
    public void revoke(RefreshToken token) {
        token.setRevoked(true);
        repository.save(token);
    }

    @Transactional
    public void revokeAllForUser(User user) {
        repository.revokeAllUserTokens(user);
    }
}
