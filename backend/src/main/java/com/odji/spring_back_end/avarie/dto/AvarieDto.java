package com.odji.spring_back_end.avarie.dto;

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
public class AvarieDto {

    private Integer id;

    private BigDecimal quantite;

    private LocalDate date;

    private String motif;

    private ProduitDto produit;
}
