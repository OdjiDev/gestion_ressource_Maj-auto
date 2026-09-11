package com.odji.spring_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDto {

    private Integer id;
    private String codeproduit;
    private String nom;
    private String designation;
    private BigDecimal quantite;

    // Relations sortantes exposées (utile côté front)
    private CategorieDto categorie;
    private MagasinDto magasin;

    // ⚠️ On N'EXPOSE PAS les relations inverses dans le DTO de Produit.
    //    Voir explication ci-dessous.
}
