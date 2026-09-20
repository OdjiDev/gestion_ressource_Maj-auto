package com.odji.spring_back_end.societe.mapper;

import com.odji.spring_back_end.societe.dto.SocieteDto;
import com.odji.spring_back_end.societe.entity.Societe;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SocieteMapper {

    public SocieteDto toDto(Societe entity) {
        if (entity == null) {
            return null;
        }

        return SocieteDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .adresse(entity.getAdresse())
                .numerofiscal(entity.getNumerofiscal())
                .build();
    }

    public List<SocieteDto> toDtoList(List<Societe> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Societe toEntity(SocieteDto dto) {
        if (dto == null) {
            return null;
        }

        Societe entity = new Societe();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setAdresse(dto.getAdresse());
       // entity.setNumerofisca(dto.getNumerofiscal());
        return entity;
    }
}
