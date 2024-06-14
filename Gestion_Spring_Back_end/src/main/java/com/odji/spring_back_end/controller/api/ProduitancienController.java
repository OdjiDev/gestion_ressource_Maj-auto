package com.odji.spring_back_end.controller.api;

import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pas")
@CrossOrigin(origins="")
public class ProduitancienController
{

//
//    @Autowired
//    private ProduitService produitService;
//
//    @PostMapping("/save")
//    public ResponseEntity<ProduitDto> createProduit(@RequestBody ProduitDto produitDto) {
//        ProduitDto createdProduit = produitService.save(produitDto);
//        return new ResponseEntity<>(createdProduit, HttpStatus.CREATED);
//    }
//


}