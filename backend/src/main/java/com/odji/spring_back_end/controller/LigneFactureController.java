package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.LigneFactureDto;
import com.odji.spring_back_end.service.LigneFactureService;
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
@RequestMapping("/api/lignes-facture")
@RequiredArgsConstructor
public class LigneFactureController {

    private final LigneFactureService ligneFactureService;

    @PostMapping
    public ResponseEntity<LigneFactureDto> create(@Valid @RequestBody LigneFactureDto dto,
                                                  UriComponentsBuilder uriBuilder) {
        LigneFactureDto created = ligneFactureService.create(dto);
        URI location = uriBuilder.path("/api/lignes-facture/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<LigneFactureDto>> findAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(ligneFactureService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneFactureDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(ligneFactureService.findById(id));
    }

    @GetMapping("/facture/{idFacture}")
    public ResponseEntity<List<LigneFactureDto>> findByFacture(@PathVariable Integer idFacture) {
        return ResponseEntity.ok(ligneFactureService.findByFacture(idFacture));
    }

    @GetMapping("/produit/{idProduit}")
    public ResponseEntity<List<LigneFactureDto>> findByProduit(@PathVariable Integer idProduit) {
        return ResponseEntity.ok(ligneFactureService.findByProduit(idProduit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneFactureDto> update(@PathVariable Integer id,
                                                  @Valid @RequestBody LigneFactureDto dto) {
        return ResponseEntity.ok(ligneFactureService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ligneFactureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
