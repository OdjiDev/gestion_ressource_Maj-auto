package com.odji.spring_back_end.affectation.dto;

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
public class MagasinDto {

    private Integer id;
    private String nom;

    // ❌ Pas de "produits" → endpoint dédié si besoin :
    // GET /api/magasins/{id}/produits
}
