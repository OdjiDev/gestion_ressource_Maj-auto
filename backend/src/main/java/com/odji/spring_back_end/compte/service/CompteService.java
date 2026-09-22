package com.odji.spring_back_end.compte.service;

import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.compte.dto.CompteCreateRequest;
import com.odji.spring_back_end.compte.dto.CompteDto;
import com.odji.spring_back_end.compte.entity.Compte;
import com.odji.spring_back_end.compte.mapper.CompteMapper;
import com.odji.spring_back_end.compte.repository.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompteService {

    private final CompteRepository repository;
    private final CompteMapper mapper;

    public List<CompteDto> findAll() {
        List<CompteDto> dtos = mapper.toDtoList(repository.findAllActive());
        dtos.forEach(dto -> dto.setSoldeActuel(dto.getSoldeInitial()));
        return dtos;
    }

    public List<CompteDto> findAllActiveAndEnabled() {
        return mapper.toDtoList(repository.findAllActiveAndEnabled());
    }

    public CompteDto findById(Long id) {
        Compte entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte", id));
        CompteDto dto = mapper.toDto(entity);
        dto.setSoldeActuel(dto.getSoldeInitial());
        return dto;
    }

    @Transactional
    public CompteDto create(CompteCreateRequest request) {
        if (repository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException(
                    "Un compte avec le code " + request.getCode() + " existe déjà");
        }

        Compte entity = mapper.toEntity(request);
        if (entity.getSoldeInitial() == null) entity.setSoldeInitial(BigDecimal.ZERO);
        if (entity.getDevise() == null || entity.getDevise().isBlank()) entity.setDevise("FCFA");
        if (entity.getActif() == null) entity.setActif(true);
        entity.setDeleted(false);

        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public CompteDto update(Long id, CompteCreateRequest request) {
        Compte entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte", id));

        if (!entity.getCode().equals(request.getCode()) && repository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException(
                    "Un compte avec le code " + request.getCode() + " existe déjà");
        }

        mapper.updateEntity(request, entity);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        Compte entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte", id));
        entity.setDeleted(true);
        repository.save(entity);
    }
}
