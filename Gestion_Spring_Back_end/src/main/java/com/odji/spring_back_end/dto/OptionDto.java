package com.odji.spring_back_end.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.model.Users;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {

    private Integer id;

    private String nom;

    @JsonIgnore
    private List<Users> users;
}
