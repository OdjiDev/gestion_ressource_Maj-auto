package com.odji.spring_back_end.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private Integer id;

    private String userId;

    private String password;

    private OptionDto roleDto;


}
