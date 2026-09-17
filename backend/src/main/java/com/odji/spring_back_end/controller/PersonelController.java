package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.PersonelDto;
import com.odji.spring_back_end.service.PersonelService;
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
@RequestMapping("/api/personels")
@RequiredArgsConstructor
public class PersonelController {

    private final PersonelService personelService;

    @PostMapping
    public ResponseEntity<PersonelDto> create(@Valid @RequestBody PersonelDto dto,
                                              UriComponentsBuilder uriBuilder) {
        PersonelDto created = personelService.create(dto);
        URI location = uriBuilder.path("/api/personels/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }
/*
    @GetMapping
    public ResponseEntity<Page<PersonelDto>> findAll(
            @PageableDefault(size = 20, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(personelService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonelDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(personelService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonelDto> update(@PathVariable Integer id,
                                              @Valid @RequestBody PersonelDto dto) {
        return ResponseEntity.ok(personelService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        personelService.delete(id);
        return ResponseEntity.noContent().build();
    }
    *
 */
}
