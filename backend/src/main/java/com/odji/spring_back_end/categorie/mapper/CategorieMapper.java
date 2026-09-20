package com.odji.spring_back_end.categorie.mapper;

import com.odji.spring_back_end.categorie.dto.CategorieDto;
import com.odji.spring_back_end.categorie.entity.Categorie;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorieMapper {

    // ==================== Entité → DTO ====================

    public CategorieDto toDto(Categorie entity) {
        if (entity == null) {
            return null;
        }

        return CategorieDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())            // ⚠️ si le champ Java s'appelle "nom"
                .code(entity.getCode())
                .designation(entity.getDesignation())
                .build();
    }

    public List<CategorieDto> toDtoList(List<Categorie> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ==================== DTO → Entité ====================

    public Categorie toEntity(CategorieDto dto) {
        if (dto == null) {
            return null;
        }

        Categorie entity = new Categorie();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());             // ⚠️ adapter
        entity.setCode(dto.getCode());
        entity.setDesignation(dto.getDesignation());
        return entity;
    }
}
