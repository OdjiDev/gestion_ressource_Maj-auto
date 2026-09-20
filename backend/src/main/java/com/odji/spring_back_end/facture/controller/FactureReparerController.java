package com.odji.spring_back_end.facture.controller;

import com.odji.spring_back_end.facture.dto.FactureReparerDto;
import com.odji.spring_back_end.facture.service.FactureReparerService;
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
@RequestMapping("/api/factures-reparer")
@RequiredArgsConstructor
public class FactureReparerController {

    private final FactureReparerService factureReparerService;

    @PostMapping
    public ResponseEntity<FactureReparerDto> create(@Valid @RequestBody FactureReparerDto dto,
                                                    UriComponentsBuilder uriBuilder) {
        FactureReparerDto created = factureReparerService.create(dto);
        URI location = uriBuilder.path("/api/factures-reparer/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<FactureReparerDto>> findAll(
            @PageableDefault(size = 20, sort = "code") Pageable pageable) {
        return ResponseEntity.ok(factureReparerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureReparerDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(factureReparerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureReparerDto> update(@PathVariable Integer id,
                                                    @Valid @RequestBody FactureReparerDto dto) {
        return ResponseEntity.ok(factureReparerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        factureReparerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
