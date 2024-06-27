package com.odji.spring_back_end.controller.api;

import com.odji.spring_back_end.dto.AffectationDto;
import com.odji.spring_back_end.model.Affectation;
import com.odji.spring_back_end.repository.AffectationRepository;
import com.odji.spring_back_end.repository.ProduitRepository;
import com.odji.spring_back_end.services.AffectationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AffectationController {

    private final AffectationService affectationService;

    private final AffectationRepository affectationRepository;
    private final ProduitRepository produitRepository;


    // create AVARIE
    @PostMapping("/affectations")
    public ResponseEntity<AffectationDto> createAffectation(@RequestBody AffectationDto affectationDto) {
        Affectation affectation = affectationService.dtoToAffectation(affectationDto);
        Affectation savedAffectation= affectationRepository.save(affectation);
        return ResponseEntity.ok(affectationService.affectationToDto(savedAffectation));
    }
    // get all Affectation
    @GetMapping("/affectations/list")
    public List<AffectationDto> getAllAffectation() {
        List<Affectation> affectations = affectationRepository.findAll(); // Assuming you have a JPA repository named 'produitRepository'
        return affectationService.affectationsDtoList(affectationRepository.findAll()); // Convert products to DTOs
    }
}

