package com.odji.spring_back_end.services;

import com.odji.spring_back_end.dto.CategorieDto;
import com.odji.spring_back_end.dto.UsersDto;
import com.odji.spring_back_end.model.Categorie;
import com.odji.spring_back_end.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class UsersService {


private  final OptionService optionService;
        public List<UsersDto> usersDtoList(List<Users> userss){
            return userss.stream()
                    .map(this::usersToDto) //utilise la methode de conversion individuel
                    .collect(Collectors.toList());

        }
        public UsersDto usersToDto(Users users) {
            if (users == null) {
                String password = users.getPassword();
                return null;
            }

            UsersDto usersDto = new UsersDto();
            usersDto.setId(users.getId());
            usersDto.setUserId(users.getUserId());
            usersDto.setPassword(users.getPassword());
           usersDto.setRoleDto(optionService.OptionToDto(users.getRole()));

            return usersDto;
        }


        public Users dtoToUsers (UsersDto usersDto) {
            if (usersDto== null) {
                return null;
            }

            Users users= new Users();
            users.setId(usersDto.getId());
            users.setPassword(usersDto.getPassword());
            users.setUserId(usersDto.getUserId());
            users.setRole(optionService.dtoToOption(usersDto.getRoleDto()));
            return users;
        }

    }




