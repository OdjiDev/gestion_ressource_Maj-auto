package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.SocieteDto;
import com.odji.spring_back_end.service.SocieteService;
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
@RequestMapping("/api/societes")
@RequiredArgsConstructor
public class SocieteController {

    private final SocieteService societeService;

    @PostMapping
    public ResponseEntity<SocieteDto> create(@Valid @RequestBody SocieteDto dto,
                                             UriComponentsBuilder uriBuilder) {
        SocieteDto created = societeService.create(dto);
        URI location = uriBuilder.path("/api/societes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<SocieteDto>> findAll(
            @PageableDefault(size = 20, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(societeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocieteDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(societeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocieteDto> update(@PathVariable Integer id,
                                             @Valid @RequestBody SocieteDto dto) {
        return ResponseEntity.ok(societeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        societeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
