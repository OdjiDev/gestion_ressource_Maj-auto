package com.odji.spring_back_end.dto;

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
public class SocieteDto {

    private Integer id;
    private String nom;
    private String adresse;
    private String numerofiscal;

    // ❌ Pas de "contrats" ni "facturereparers" → relations inverses
    //    Endpoints dédiés :
    //    GET /api/societes/{id}/contrats
    //    GET /api/societes/{id}/factures-reparer
}
