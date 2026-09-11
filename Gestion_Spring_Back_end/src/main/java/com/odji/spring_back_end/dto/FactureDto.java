package com.odji.spring_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactureDto {

    private Integer id;
    private String numero;
    private String code;
    private LocalDate datecommande;
    private BigDecimal total;

    // Relation sortante exposée (sans suffixe "Dto")
    private FournisseurDto fournisseur;

    // ❌ Pas de "lignefactures" → endpoint dédié si besoin
}
