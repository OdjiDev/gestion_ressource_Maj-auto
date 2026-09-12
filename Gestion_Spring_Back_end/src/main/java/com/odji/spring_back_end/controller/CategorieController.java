package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.CategorieDto;
import com.odji.spring_back_end.service.CategorieService;
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
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    // ==================== CRÉATION ====================

    @PostMapping
    public ResponseEntity<CategorieDto> create(
            @Valid @RequestBody CategorieDto dto,
            UriComponentsBuilder uriBuilder) {
        CategorieDto created = categorieService.create(dto);
        URI location = uriBuilder.path("/api/categories/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    // ==================== LECTURE ====================

    @GetMapping
    public ResponseEntity<Page<CategorieDto>> findAll(
            @PageableDefault(size = 20, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(categorieService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(categorieService.findById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CategorieDto> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(categorieService.findByCode(code));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategorieDto>> search(@RequestParam String nom) {
        return ResponseEntity.ok(categorieService.searchByNom(nom));
    }

    // ==================== MISE À JOUR ====================

    @PutMapping("/{id}")
    public ResponseEntity<CategorieDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody CategorieDto dto) {
        return ResponseEntity.ok(categorieService.update(id, dto));
    }

    // ==================== SUPPRESSION ====================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
