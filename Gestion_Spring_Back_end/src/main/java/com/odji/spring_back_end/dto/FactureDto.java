package com.odji.spring_back_end.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FactureDto {

    private Integer id;


    private String numero;


    private String code;


    private Instant datecommande;

    private String total;



    private FournisseurDto fournisseurDto;

    @JsonIgnore
    private List<LigneFactureDto> lignefactures;


}
