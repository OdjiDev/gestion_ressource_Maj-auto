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
public class FactureReparerDto {

    private Integer id;
    private String code;
    private LocalDate date;

    // Relation sortante exposée (sans suffixe "Dto")
    private SocieteDto societe;

    // ❌ Pas de "lignesFactureReparer" → endpoint dédié si besoin
}
