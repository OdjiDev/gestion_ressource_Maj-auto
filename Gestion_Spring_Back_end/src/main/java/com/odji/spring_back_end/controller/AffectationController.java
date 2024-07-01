package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.AffectationDto;
import com.odji.spring_back_end.dto.AffectationDto;
import com.odji.spring_back_end.exeption.ResourceNotFoundException;
import com.odji.spring_back_end.model.Affectation;
import com.odji.spring_back_end.model.Affectation;
import com.odji.spring_back_end.repository.AffectationRepository;
import com.odji.spring_back_end.repository.ProduitRepository;
import com.odji.spring_back_end.services.AffectationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

  

    //get affectation by id
    @GetMapping("affectations/{id}")
    public ResponseEntity<Affectation> getAffectationById(@PathVariable Integer id) {
        Optional<Affectation> optionalAffectation = affectationRepository.findById(id);

        if (optionalAffectation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalAffectation.get());
    }
    // Update a category
    @PutMapping("affectations/update/{id}")
    public ResponseEntity<AffectationDto> updateAffectation(@PathVariable Integer id,@RequestBody AffectationDto affectationDetailsDto) {
        Affectation updateAffectation = affectationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affectation not exist with id: " + id));
        updateAffectation = affectationService.dtoToAffectation(affectationDetailsDto);
        updateAffectation.setId(id);

        affectationDetailsDto= affectationService.affectationToDto(affectationRepository.save(updateAffectation));

        return ResponseEntity.ok(affectationDetailsDto);
    }
    

    // build delete inscription REST API
    @DeleteMapping("affectations/{id}")
    public ResponseEntity<HttpStatus> deleteAffectation(@PathVariable Integer id){

        Affectation affectation = affectationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("affectation  not exist with id: " + id));

        affectationRepository.delete(affectation);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}




