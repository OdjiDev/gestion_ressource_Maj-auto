package com.odji.spring_back_end.facture.controller;

import com.odji.spring_back_end.facture.dto.LigneFactureReparerDto;
import com.odji.spring_back_end.facture.service.LigneFactureReparerService;
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
@RequestMapping("/api/lignes-facture-reparer")
@RequiredArgsConstructor
public class LigneFactureReparerController {

    private final LigneFactureReparerService ligneFactureReparerService;

    @PostMapping
    public ResponseEntity<LigneFactureReparerDto> create(
            @Valid @RequestBody LigneFactureReparerDto dto,
            UriComponentsBuilder uriBuilder) {
        LigneFactureReparerDto created = ligneFactureReparerService.create(dto);
        URI location = uriBuilder.path("/api/lignes-facture-reparer/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<LigneFactureReparerDto>> findAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(ligneFactureReparerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneFactureReparerDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(ligneFactureReparerService.findById(id));
    }

    @GetMapping("/facture/{idFacture}")
    public ResponseEntity<List<LigneFactureReparerDto>> findByFacture(
            @PathVariable Integer idFacture) {
        return ResponseEntity.ok(ligneFactureReparerService.findByFactureReparer(idFacture));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneFactureReparerDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody LigneFactureReparerDto dto) {
        return ResponseEntity.ok(ligneFactureReparerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ligneFactureReparerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
