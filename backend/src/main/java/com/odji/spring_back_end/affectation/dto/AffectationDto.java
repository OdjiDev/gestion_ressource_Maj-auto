package com.odji.spring_back_end.affectation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffectationDto {

    private Integer id;

    private BigDecimal quantite;

    private LocalDate date;

    private String motif;

    // Relations sortantes exposées (sans le suffixe "Dto")
    private ProduitDto produit;
    private PersonelDto personel;
}
