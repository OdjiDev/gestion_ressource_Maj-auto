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
public class BureauDto {

    private Integer id;
    private String nom;

    // Relation sortante exposée (sans suffixe "Dto")
    private DepartementDto departement;

    // ❌ Pas de "demandes" → relations inverses, endpoint dédié :
    //    GET /api/bureaux/{id}/demandes
}
