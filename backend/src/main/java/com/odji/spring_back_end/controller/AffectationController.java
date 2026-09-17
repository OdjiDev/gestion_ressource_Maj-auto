package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.AffectationDto;
import com.odji.spring_back_end.service.AffectationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/affectations")
@RequiredArgsConstructor
public class AffectationController {

    private final AffectationService affectationService;

    @PostMapping
    public ResponseEntity<AffectationDto> create(@Valid @RequestBody AffectationDto dto,
                                                 UriComponentsBuilder uriBuilder) {
        AffectationDto created = affectationService.create(dto);
        URI location = uriBuilder.path("/api/affectations/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<AffectationDto>> findAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(affectationService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AffectationDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(affectationService.findById(id));
    }

    @GetMapping("/produit/{idProduit}")
    public ResponseEntity<List<AffectationDto>> findByProduit(@PathVariable Integer idProduit) {
        return ResponseEntity.ok(affectationService.findByProduit(idProduit));
    }

    @GetMapping("/personel/{idPersonel}")
    public ResponseEntity<List<AffectationDto>> findByPersonel(@PathVariable Integer idPersonel) {
        return ResponseEntity.ok(affectationService.findByPersonel(idPersonel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AffectationDto> update(@PathVariable Integer id,
                                                 @Valid @RequestBody AffectationDto dto) {
        return ResponseEntity.ok(affectationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        affectationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
