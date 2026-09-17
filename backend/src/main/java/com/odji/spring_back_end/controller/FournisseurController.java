package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.FournisseurDto;
import com.odji.spring_back_end.service.FournisseurService;
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
@RequestMapping("/api/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    private final FournisseurService fournisseurService;

    @PostMapping
    public ResponseEntity<FournisseurDto> create(@Valid @RequestBody FournisseurDto dto,
                                                 UriComponentsBuilder uriBuilder) {
        FournisseurDto created = fournisseurService.create(dto);
        URI location = uriBuilder.path("/api/fournisseurs/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<FournisseurDto>> findAll(
            @PageableDefault(size = 20, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(fournisseurService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FournisseurDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(fournisseurService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FournisseurDto>> search(@RequestParam String nom) {
        return ResponseEntity.ok(fournisseurService.searchByNom(nom));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FournisseurDto> update(@PathVariable Integer id,
                                                 @Valid @RequestBody FournisseurDto dto) {
        return ResponseEntity.ok(fournisseurService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        fournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
