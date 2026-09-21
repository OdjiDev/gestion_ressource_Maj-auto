package com.odji.spring_back_end.user.controller;

import com.odji.spring_back_end.user.entity.Personel;
import com.odji.spring_back_end.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class employeeController{

    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("employees")

    //get all employees

    public List<Personel>getAllEmployees(){
        return employeeRepository.findAll();
    }



}



