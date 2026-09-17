package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.DepartementDto;
import com.odji.spring_back_end.service.DepartementService;
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
@RequestMapping("/api/departements")
@RequiredArgsConstructor
public class DepartementController {

    private final DepartementService departementService;

    @PostMapping
    public ResponseEntity<DepartementDto> create(@Valid @RequestBody DepartementDto dto,
                                                 UriComponentsBuilder uriBuilder) {
        DepartementDto created = departementService.create(dto);
        URI location = uriBuilder.path("/api/departements/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<DepartementDto>> findAll(
            @PageableDefault(size = 20, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(departementService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartementDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(departementService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartementDto> update(@PathVariable Integer id,
                                                 @Valid @RequestBody DepartementDto dto) {
        return ResponseEntity.ok(departementService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        departementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
