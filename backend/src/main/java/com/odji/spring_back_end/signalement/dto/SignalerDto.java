package com.odji.spring_back_end.signalement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.odji.spring_back_end.produit.dto.ProduitDto;
import com.odji.spring_back_end.user.dto.PersonelDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignalerDto {

    private Integer id;
    private String etat;

    // Relations sortantes exposées en DTO (pas en entité !)
    private ProduitDto produit;
    private PersonelDto personel;
}
