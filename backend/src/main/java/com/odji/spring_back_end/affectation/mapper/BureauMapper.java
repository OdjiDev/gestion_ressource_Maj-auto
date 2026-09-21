package com.odji.spring_back_end.affectation.mapper;

import com.odji.spring_back_end.affectation.dto.BureauDto;
import com.odji.spring_back_end.affectation.dto.DepartementDto;
import com.odji.spring_back_end.affectation.entity.Bureau;
import com.odji.spring_back_end.affectation.entity.Departement;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BureauMapper {

    public BureauDto toDto(Bureau entity) {
        if (entity == null) return null;
        return BureauDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .departement(toDepartementDto(entity.getDepartement()))
                .build();
    }

    public List<BureauDto> toDtoList(List<Bureau> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Bureau toEntity(BureauDto dto) {
        if (dto == null) return null;
        Bureau entity = new Bureau();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        return entity;
    }

    private DepartementDto toDepartementDto(Departement dep) {
        if (dep == null) return null;
        return DepartementDto.builder()
                .id(dep.getId())
                .nom(dep.getNom())
                .code(dep.getCode())
                .build();
    }
}
