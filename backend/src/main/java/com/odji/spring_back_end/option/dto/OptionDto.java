package com.odji.spring_back_end.option.dto;

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
public class OptionDto {

    private Integer id;
    private String nom;

    // ❌ Pas de "personels" → relation inverse, endpoint dédié :
    //    GET /api/options/{id}/personels
}
