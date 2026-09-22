package com.odji.spring_back_end.compte.controller;

import com.odji.spring_back_end.compte.dto.CompteCreateRequest;
import com.odji.spring_back_end.compte.dto.CompteDto;
import com.odji.spring_back_end.compte.service.CompteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService service;

    @PostMapping
    public ResponseEntity<CompteDto> create(
            @Valid @RequestBody CompteCreateRequest request,
            UriComponentsBuilder uriBuilder) {
        CompteDto created = service.create(request);
        URI location = uriBuilder.path("/api/comptes/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompteDto>> findAllList() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/actifs")
    public ResponseEntity<List<CompteDto>> findAllActifs() {
        return ResponseEntity.ok(service.findAllActiveAndEnabled());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompteDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CompteCreateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
