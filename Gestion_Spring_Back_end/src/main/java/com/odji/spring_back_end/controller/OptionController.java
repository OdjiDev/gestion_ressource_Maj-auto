package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.OptionDto;
import com.odji.spring_back_end.service.OptionService;
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
@RequestMapping("/api/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @PostMapping
    public ResponseEntity<OptionDto> create(@Valid @RequestBody OptionDto dto,
                                            UriComponentsBuilder uriBuilder) {
        OptionDto created = optionService.create(dto);
        URI location = uriBuilder.path("/api/options/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<OptionDto>> findAll(
            @PageableDefault(size = 20, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(optionService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(optionService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionDto> update(@PathVariable Integer id,
                                            @Valid @RequestBody OptionDto dto) {
        return ResponseEntity.ok(optionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        optionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
