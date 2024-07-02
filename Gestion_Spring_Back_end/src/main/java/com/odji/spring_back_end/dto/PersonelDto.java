package com.odji.spring_back_end.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.model.Affectation;
import lombok.*;

import java.util.List;

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

    @JsonIgnore
    private List<Affectation> affectation;
}
