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
public class LigneDemandeDto {

    private Integer id;
    private BigDecimal quantite;
    private LocalDate date;

    // Relation sortante exposée (sans suffixe "Dto")
    private ProduitDto produit;

    // ❌ Pas de "demandes" → incohérence métier, voir plus bas
}
