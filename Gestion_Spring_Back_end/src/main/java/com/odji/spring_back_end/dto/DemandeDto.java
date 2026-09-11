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
public class DemandeDto {

    private Integer id;
    private String motif;

    // Relations sortantes exposées (sans suffixe "Dto")
    private LigneDemandeDto ligneDemande;
    private BureauDto bureau;
}
