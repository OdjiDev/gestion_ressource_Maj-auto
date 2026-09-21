package com.odji.spring_back_end.affectation.mapper;

import com.odji.spring_back_end.affectation.dto.MagasinDto;
import com.odji.spring_back_end.affectation.entity.Magasin;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MagasinMapper {

    public MagasinDto toDto(Magasin entity) {
        if (entity == null) {
            return null;
        }

        return MagasinDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .build();
    }

    public List<MagasinDto> toDtoList(List<Magasin> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Magasin toEntity(MagasinDto dto) {
        if (dto == null) {
            return null;
        }

        Magasin entity = new Magasin();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        return entity;
    }
}
