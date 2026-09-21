package com.odji.spring_back_end.signalement.controller;

import com.odji.spring_back_end.signalement.dto.SignalerDto;
import com.odji.spring_back_end.signalement.service.SignalerService;
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
@RequestMapping("/api/signalers")
@RequiredArgsConstructor
public class SignalerController {

    private final SignalerService signalerService;

    @PostMapping
    public ResponseEntity<SignalerDto> create(@Valid @RequestBody SignalerDto dto,
                                              UriComponentsBuilder uriBuilder) {
        SignalerDto created = signalerService.create(dto);
        URI location = uriBuilder.path("/api/signalers/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<SignalerDto>> findAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(signalerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignalerDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(signalerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SignalerDto> update(@PathVariable Integer id,
                                              @Valid @RequestBody SignalerDto dto) {
        return ResponseEntity.ok(signalerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        signalerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
