package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.model.Users;
import com.odji.spring_back_end.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins="http://localhost:4200/")
public class UsersController {

         @Autowired
        private UsersRepository repository;
//

        @PostMapping ("/login")
        public ResponseEntity<?> loginUser(@RequestBody Users usersData){
            System.out.println(usersData);
    Users users=repository.findByUserId(usersData.getUserId());
    if(users.getPassword().equals(usersData.getPassword()))
      return ResponseEntity.ok(users);
        return (ResponseEntity<?>)ResponseEntity.internalServerError();
    }

    }