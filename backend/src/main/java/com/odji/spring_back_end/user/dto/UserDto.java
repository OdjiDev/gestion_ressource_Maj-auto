package com.odji.spring_back_end.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String userId;

    // ⚠️ Ne JAMAIS exposer le password dans un DTO de sortie
    // Si besoin en entrée (register), utilise un DTO dédié RegisterRequest

    private String userRole;
    private String email;
}
