package com.odji.spring_back_end.dto;

import com.odji.spring_back_end.model.Users;
import jakarta.persistence.Column;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private Long id;

    private String userId;

    private String password;


}
