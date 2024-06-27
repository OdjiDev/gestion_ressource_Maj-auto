package com.odji.spring_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AffectationDto {

    private Integer id;

    private BigDecimal quantite;

    private Date date;

    private String motif;

    private ProduitDto produitDto;
    private PersonelDto personelDto;

}
