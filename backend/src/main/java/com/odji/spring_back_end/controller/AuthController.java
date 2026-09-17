package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.auth.AuthResponse;
import com.odji.spring_back_end.dto.auth.LoginRequest;
import com.odji.spring_back_end.dto.auth.RegisterRequest;
import com.odji.spring_back_end.exception.DuplicateResourceException;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.model.Personel;
import com.odji.spring_back_end.model.Role;
import com.odji.spring_back_end.model.User;
import com.odji.spring_back_end.repository.PersonelRepository;
import com.odji.spring_back_end.repository.UserRepository;
import com.odji.spring_back_end.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final AuthenticationManager authenticationManager;

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
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Personel", req.getPersonelId()));
            user.setPersonel(personel);
        }

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

        User user = userRepository.findByEmail(req.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getUserRole().name())
                .build());
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me(
            @org.springframework.security.core.annotation.AuthenticationPrincipal User user) {
        return ResponseEntity.ok(AuthResponse.builder()
                .email(user.getEmail())
                .build());
    }
}
