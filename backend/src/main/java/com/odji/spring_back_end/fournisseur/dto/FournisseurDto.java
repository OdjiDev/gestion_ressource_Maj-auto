package com.odji.spring_back_end.fournisseur.dto;

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
public class FournisseurDto {

    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String mail;
    private String numtel;

    // ❌ Pas de "factures" → relation inverse, endpoint dédié :
    //    GET /api/fournisseurs/{id}/factures
}
