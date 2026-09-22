package com.odji.spring_back_end.compte.dto;

import com.odji.spring_back_end.compte.enums.TypeCompte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompteCreateRequest {

    @NotBlank(message = "Le code est obligatoire")
    private String code;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le type est obligatoire")
    private TypeCompte type;

    private BigDecimal soldeInitial;

    private String devise;

    private BigDecimal seuilAlerte;

    private Boolean actif;
}
