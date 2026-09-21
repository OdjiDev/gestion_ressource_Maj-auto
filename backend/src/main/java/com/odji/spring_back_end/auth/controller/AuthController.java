package com.odji.spring_back_end.auth.controller;

import com.odji.spring_back_end.auth.dto.AuthResponse;
import com.odji.spring_back_end.auth.dto.LoginRequest;
import com.odji.spring_back_end.auth.dto.RegisterRequest;
import com.odji.spring_back_end.auth.dto.TokenRefreshRequest;
import com.odji.spring_back_end.auth.entity.RefreshToken;
import com.odji.spring_back_end.auth.service.RefreshTokenService;
import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.security.JwtService;
import com.odji.spring_back_end.user.entity.Personel;
import com.odji.spring_back_end.user.entity.Role;
import com.odji.spring_back_end.user.entity.User;
import com.odji.spring_back_end.user.repository.PersonelRepository;
import com.odji.spring_back_end.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PersonelRepository personelRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @Value("${app.jwt.expiration-ms:900000}")
    private long accessExpirationMs;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new DuplicateResourceException("Email déjà utilisé : " + req.getEmail());
        }
        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .userRole(req.getRole() != null ? req.getRole() : Role.USER)
                .enabled(true)
                .build();
        if (req.getPersonelId() != null) {
            Personel personel = personelRepository.findById(req.getPersonelId())
                    .orElseThrow(() -> new ResourceNotFoundException("Personel", req.getPersonelId()));
            user.setPersonel(personel);
        }
        userRepository.save(user);
        return ResponseEntity.ok(buildAuthResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow();
        return ResponseEntity.ok(buildAuthResponse(user));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody TokenRefreshRequest req) {
        RefreshToken oldToken = refreshTokenService.verify(req.getRefreshToken());
        RefreshToken newToken = refreshTokenService.rotate(oldToken);
        User user = newToken.getUser();
        String accessToken = jwtService.generateToken(user);
        return ResponseEntity.ok(AuthResponse.builder()
                .token(accessToken)
                .refreshToken(newToken.getToken())
                .email(user.getEmail())
                .role(user.getUserRole().name())
                .expiresIn(accessExpirationMs)
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody TokenRefreshRequest req) {
        try {
            RefreshToken token = refreshTokenService.verify(req.getRefreshToken());
            refreshTokenService.revoke(token);
        } catch (Exception ignored) { }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getUserRole().name())
                .build());
    }

    private AuthResponse buildAuthResponse(User user) {
        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.create(user);
        return AuthResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken.getToken())
                .email(user.getEmail())
                .role(user.getUserRole().name())
                .expiresIn(accessExpirationMs)
                .build();
    }
}
