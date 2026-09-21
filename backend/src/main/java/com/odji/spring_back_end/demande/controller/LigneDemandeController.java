package com.odji.spring_back_end.demande.controller;

import com.odji.spring_back_end.demande.dto.LigneDemandeDto;
import com.odji.spring_back_end.demande.service.LigneDemandeService;
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
@RequestMapping("/api/lignes-demande")
@RequiredArgsConstructor
public class LigneDemandeController {

    private final LigneDemandeService ligneDemandeService;

    @PostMapping
    public ResponseEntity<LigneDemandeDto> create(
            @Valid @RequestBody LigneDemandeDto dto,
            UriComponentsBuilder uriBuilder) {
        LigneDemandeDto created = ligneDemandeService.create(dto);
        URI location = uriBuilder.path("/api/lignes-demande/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<LigneDemandeDto>> findAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(ligneDemandeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneDemandeDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(ligneDemandeService.findById(id));
    }

    @GetMapping("/demande/{idDemande}")
    public ResponseEntity<List<LigneDemandeDto>> findByDemande(@PathVariable Integer idDemande) {
        return ResponseEntity.ok(ligneDemandeService.findByDemande(idDemande));
    }

    @GetMapping("/produit/{idProduit}")
    public ResponseEntity<List<LigneDemandeDto>> findByProduit(@PathVariable Integer idProduit) {
        return ResponseEntity.ok(ligneDemandeService.findByProduit(idProduit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneDemandeDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody LigneDemandeDto dto) {
        return ResponseEntity.ok(ligneDemandeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ligneDemandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
