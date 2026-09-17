package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.FactureDto;
import com.odji.spring_back_end.service.FactureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {

    private final FactureService factureService;

    @PostMapping
    public ResponseEntity<FactureDto> create(@Valid @RequestBody FactureDto dto,
                                             UriComponentsBuilder uriBuilder) {
        FactureDto created = factureService.create(dto);
        URI location = uriBuilder.path("/api/factures/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<FactureDto>> findAll(
            @PageableDefault(size = 20, sort = "numero") Pageable pageable) {
        return ResponseEntity.ok(factureService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(factureService.findById(id));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<FactureDto> findByNumero(@PathVariable String numero) {
        return ResponseEntity.ok(factureService.findByNumero(numero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureDto> update(@PathVariable Integer id,
                                             @Valid @RequestBody FactureDto dto) {
        return ResponseEntity.ok(factureService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        factureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
