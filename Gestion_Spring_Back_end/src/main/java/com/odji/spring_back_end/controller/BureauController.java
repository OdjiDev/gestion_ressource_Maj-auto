package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.BureauDto;
import com.odji.spring_back_end.service.BureauService;
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
@RequestMapping("/api/bureaux")
@RequiredArgsConstructor
public class BureauController {

    private final BureauService bureauService;

    @PostMapping
    public ResponseEntity<BureauDto> create(@Valid @RequestBody BureauDto dto,
                                            UriComponentsBuilder uriBuilder) {
        BureauDto created = bureauService.create(dto);
        URI location = uriBuilder.path("/api/bureaux/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<BureauDto>> findAll(
            @PageableDefault(size = 20, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(bureauService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BureauDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(bureauService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BureauDto>> search(@RequestParam String nom) {
        return ResponseEntity.ok(bureauService.searchByNom(nom));
    }

    @GetMapping("/departement/{idDepartement}")
    public ResponseEntity<List<BureauDto>> findByDepartement(@PathVariable Integer idDepartement) {
        return ResponseEntity.ok(bureauService.findByDepartement(idDepartement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BureauDto> update(@PathVariable Integer id,
                                            @Valid @RequestBody BureauDto dto) {
        return ResponseEntity.ok(bureauService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bureauService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
