package com.odji.spring_back_end.societe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContratDto {

    private Integer id;
    private String code;
    private LocalDate datedebut;
    private LocalDate datedefin;

    // Relation sortante exposée (sans suffixe "Dto")
    private SocieteDto societe;
}
