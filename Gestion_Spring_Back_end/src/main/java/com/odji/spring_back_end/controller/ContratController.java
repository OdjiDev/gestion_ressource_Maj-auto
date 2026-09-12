package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.ContratDto;
import com.odji.spring_back_end.service.ContratService;
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
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final ContratService contratService;

    @PostMapping
    public ResponseEntity<ContratDto> create(@Valid @RequestBody ContratDto dto,
                                             UriComponentsBuilder uriBuilder) {
        ContratDto created = contratService.create(dto);
        URI location = uriBuilder.path("/api/contrats/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<ContratDto>> findAll(
            @PageableDefault(size = 20, sort = "code") Pageable pageable) {
        return ResponseEntity.ok(contratService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(contratService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratDto> update(@PathVariable Integer id,
                                             @Valid @RequestBody ContratDto dto) {
        return ResponseEntity.ok(contratService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        contratService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
