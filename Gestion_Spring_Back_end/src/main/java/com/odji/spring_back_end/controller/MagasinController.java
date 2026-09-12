package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.MagasinDto;
import com.odji.spring_back_end.service.MagasinService;
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
@RequestMapping("/api/magasins")
@RequiredArgsConstructor
public class MagasinController {

    private final MagasinService magasinService;

    @PostMapping
    public ResponseEntity<MagasinDto> create(@Valid @RequestBody MagasinDto dto,
                                             UriComponentsBuilder uriBuilder) {
        MagasinDto created = magasinService.create(dto);
        URI location = uriBuilder.path("/api/magasins/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<MagasinDto>> findAll(
            @PageableDefault(size = 20, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(magasinService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MagasinDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(magasinService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MagasinDto>> search(@RequestParam String nom) {
        return ResponseEntity.ok(magasinService.searchByNom(nom));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MagasinDto> update(@PathVariable Integer id,
                                             @Valid @RequestBody MagasinDto dto) {
        return ResponseEntity.ok(magasinService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        magasinService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
