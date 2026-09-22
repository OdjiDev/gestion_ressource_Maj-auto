package com.odji.spring_back_end.mouvement.controller;

import com.odji.spring_back_end.mouvement.dto.MouvementCreateRequest;
import com.odji.spring_back_end.mouvement.dto.MouvementDto;
import com.odji.spring_back_end.mouvement.dto.SoldeDto;
import com.odji.spring_back_end.mouvement.service.MouvementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/mouvements")
@RequiredArgsConstructor
public class MouvementController {

    private final MouvementService service;

    @PostMapping
    public ResponseEntity<MouvementDto> create(
            @Valid @RequestBody MouvementCreateRequest request,
            UriComponentsBuilder uriBuilder) {
        MouvementDto created = service.create(request);
        URI location = uriBuilder.path("/api/mouvements/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MouvementDto>> findAllList() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/compte/{compteId}")
    public ResponseEntity<List<MouvementDto>> findByCompte(@PathVariable Long compteId) {
        return ResponseEntity.ok(service.findByCompte(compteId));
    }

    @GetMapping("/solde/{compteId}")
    public ResponseEntity<SoldeDto> getSolde(@PathVariable Long compteId) {
        return ResponseEntity.ok(service.getSolde(compteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MouvementDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MouvementDto> update(
            @PathVariable Long id,
            @Valid @RequestBody MouvementCreateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
