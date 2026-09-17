package com.odji.spring_back_end.dto;

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
public class CategorieDto {

    private Integer id;
    private String nom;
    private String code;
    private String designation;
}
