package com.odji.spring_back_end.dto;

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
public class ReparerDto {

    private Integer id;
    private String motif;
    private LocalDate date;

    // ❌ Pas de "lignereparations" ni de "lignefactureReparers"
    //    → relations inverses, à consulter via endpoints dédiés :
    //       GET /api/reparers/{id}/lignes-reparation
    //       GET /api/reparers/{id}/lignes-facture-reparer
}
