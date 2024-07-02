package com.odji.spring_back_end.dto;

import com.odji.spring_back_end.model.Categorie;
import com.odji.spring_back_end.model.Contrat;
import com.odji.spring_back_end.model.Societe;

import lombok.*;

import java.time.Instant;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContratDto {

    private Integer id;

    private String code;

    private Instant datedebut;

    private Instant datedefin;

    private SocieteDto societeDto;
}