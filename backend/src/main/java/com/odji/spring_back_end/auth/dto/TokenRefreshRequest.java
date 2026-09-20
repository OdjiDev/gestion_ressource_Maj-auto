package com.odji.spring_back_end.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class TokenRefreshRequest {
    @NotBlank(message = "Le refresh token est obligatoire")
    private String refreshToken;
}
