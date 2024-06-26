package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.UsersDto;
import com.odji.spring_back_end.model.Users;
import com.odji.spring_back_end.model.Users;
import com.odji.spring_back_end.repository.UsersRepository;
import com.odji.spring_back_end.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins="http://localhost:4200/")
public class UsersController {

    @Autowired
        private UsersRepository usersRepository;
    @Autowired
         private   UsersService usersService;
         //private UsersService

        @PostMapping ("/login")
        public ResponseEntity<?> loginUser(@RequestBody Users usersData){
            System.out.println(usersData);
    Users users=usersRepository.findByUserId(usersData.getUserId());
    if(users.getPassword().equals(usersData.getPassword()))
      return ResponseEntity.ok(users);
        return (ResponseEntity<?>)ResponseEntity.internalServerError();
    }
    // create USERS
    @PostMapping("userss")
    public ResponseEntity<UsersDto> createUsers(@RequestBody UsersDto usersDto) {
        Users users = usersService.dtoToUsers(usersDto);
        Users savedUsers = usersRepository.save(users);
        return ResponseEntity.ok(usersService.usersToDto(savedUsers));
    }

    }