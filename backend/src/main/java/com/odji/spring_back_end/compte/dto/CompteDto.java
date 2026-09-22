package com.odji.spring_back_end.compte.dto;

import com.odji.spring_back_end.compte.enums.TypeCompte;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompteDto {
    private Long id;
    private String code;
    private String nom;
    private TypeCompte type;
    private BigDecimal soldeInitial;
    private String devise;
    private BigDecimal seuilAlerte;
    private Boolean actif;
    private BigDecimal soldeActuel;
}
