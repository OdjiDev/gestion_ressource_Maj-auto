package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.AvarieDto;
import com.odji.spring_back_end.service.AvarieService;
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
@RequestMapping("/api/avaries")
@RequiredArgsConstructor
public class AvarieController {

    private final AvarieService avarieService;

    @PostMapping
    public ResponseEntity<AvarieDto> create(@Valid @RequestBody AvarieDto dto,
                                            UriComponentsBuilder uriBuilder) {
        AvarieDto created = avarieService.create(dto);
        URI location = uriBuilder.path("/api/avaries/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<AvarieDto>> findAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(avarieService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvarieDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(avarieService.findById(id));
    }

    @GetMapping("/produit/{idProduit}")
    public ResponseEntity<List<AvarieDto>> findByProduit(@PathVariable Integer idProduit) {
        return ResponseEntity.ok(avarieService.findByProduit(idProduit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvarieDto> update(@PathVariable Integer id,
                                            @Valid @RequestBody AvarieDto dto) {
        return ResponseEntity.ok(avarieService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        avarieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
