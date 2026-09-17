package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.LigneReparationDto;
import com.odji.spring_back_end.service.LigneReparationService;
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
@RequestMapping("/api/lignes-reparation")
@RequiredArgsConstructor
public class LigneReparationController {

    private final LigneReparationService ligneReparationService;

    @PostMapping
    public ResponseEntity<LigneReparationDto> create(@Valid @RequestBody LigneReparationDto dto,
                                                     UriComponentsBuilder uriBuilder) {
        LigneReparationDto created = ligneReparationService.create(dto);
        URI location = uriBuilder.path("/api/lignes-reparation/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<LigneReparationDto>> findAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(ligneReparationService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneReparationDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(ligneReparationService.findById(id));
    }

    @GetMapping("/reparer/{idReparer}")
    public ResponseEntity<List<LigneReparationDto>> findByReparer(@PathVariable Integer idReparer) {
        return ResponseEntity.ok(ligneReparationService.findByReparer(idReparer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneReparationDto> update(@PathVariable Integer id,
                                                     @Valid @RequestBody LigneReparationDto dto) {
        return ResponseEntity.ok(ligneReparationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ligneReparationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
