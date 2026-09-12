package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.DepartementDto;
import com.odji.spring_back_end.model.Departement;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartementMapper {

    public DepartementDto toDto(Departement entity) {
        if (entity == null) return null;
        return DepartementDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .code(entity.getCode())
                .build();
    }

    public List<DepartementDto> toDtoList(List<Departement> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Departement toEntity(DepartementDto dto) {
        if (dto == null) return null;
        Departement entity = new Departement();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setCode(dto.getCode());
        return entity;
    }
}
