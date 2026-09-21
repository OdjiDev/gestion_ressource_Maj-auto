package com.odji.spring_back_end.demande.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.odji.spring_back_end.affectation.dto.BureauDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDto {

    private Integer id;
    private String motif;

    // Relations sortantes exposées (sans suffixe "Dto")
    private LigneDemandeDto ligneDemande;
    @Builder.Default
    private List<LigneDemandeDto> lignesDemande = new ArrayList<>();

    private BureauDto bureau;
}
