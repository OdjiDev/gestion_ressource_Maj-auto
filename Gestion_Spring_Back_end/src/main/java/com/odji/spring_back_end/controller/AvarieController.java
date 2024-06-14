package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.AvarieDto;
import com.odji.spring_back_end.dto.CategorieDto;
import com.odji.spring_back_end.model.Avarie;
import com.odji.spring_back_end.model.Categorie;
import com.odji.spring_back_end.model.Produit;
import com.odji.spring_back_end.repository.AvarieRepository;
import com.odji.spring_back_end.repository.ProduitRepository;
import com.odji.spring_back_end.services.AvarieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AvarieController {

    private final AvarieService avarieService;

    private final AvarieRepository avarieRepository;
    private final ProduitRepository produitRepository;


    // create AVARIE
    @PostMapping("/avaries")
    public ResponseEntity<AvarieDto> createAvarie(@RequestBody AvarieDto avarieDto) {
        Avarie avarie = avarieService.dtoToAvarie(avarieDto);
        Avarie savedAvarie= avarieRepository.save(avarie);
        return ResponseEntity.ok(avarieService.avarieToDto(savedAvarie));
    }
    // get all Avarie
    @GetMapping("/avaries/list")
    public List<AvarieDto> getAllAvarie() {
        List<Avarie> avaries = avarieRepository.findAll(); // Assuming you have a JPA repository named 'produitRepository'
        return avarieService.avariesDtoList(avarieRepository.findAll()); // Convert products to DTOs
    }
    }