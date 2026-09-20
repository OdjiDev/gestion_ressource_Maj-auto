package com.odji.spring_back_end.user.dto;

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
public class PersonelDto {

    private Integer id;
    private String nom;
    // ⚠️ adapte selon les vrais champs de Personel
}
