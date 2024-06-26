package com.odji.spring_back_end.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonelDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String dateDeNaissance;

    private String lieuDeNaissance;

    private String sexe;

    private String numero;


    private String email;

    private String password;

    private OptionDto role;
}
