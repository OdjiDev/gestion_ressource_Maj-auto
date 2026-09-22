package com.odji.spring_back_end.mouvement.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoldeDto {
    private Long compteId;
    private String compteNom;
    private BigDecimal soldeInitial;
    private BigDecimal totalEntrees;
    private BigDecimal totalSorties;
    private BigDecimal soldeActuel;
    private String devise;
}
