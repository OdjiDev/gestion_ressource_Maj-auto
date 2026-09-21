package com.odji.spring_back_end.avarie.controller;

import com.odji.spring_back_end.avarie.dto.ReparerDto;
import com.odji.spring_back_end.avarie.service.ReparerService;
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
@RequestMapping("/api/reparers")
@RequiredArgsConstructor
public class ReparerController {

    private final ReparerService reparerService;

    @PostMapping
    public ResponseEntity<ReparerDto> create(@Valid @RequestBody ReparerDto dto,
                                             UriComponentsBuilder uriBuilder) {
        ReparerDto created = reparerService.create(dto);
        URI location = uriBuilder.path("/api/reparers/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<ReparerDto>> findAll(
            @PageableDefault(size = 20, sort = "date") Pageable pageable) {
        return ResponseEntity.ok(reparerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReparerDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(reparerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReparerDto> update(@PathVariable Integer id,
                                             @Valid @RequestBody ReparerDto dto) {
        return ResponseEntity.ok(reparerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        reparerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
