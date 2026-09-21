package com.odji.spring_back_end.auth.dto;

import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private String token;
    private String refreshToken;
    @Builder.Default
    private String type = "Bearer";
    private String email;
    private String role;
    private Long expiresIn;
}
