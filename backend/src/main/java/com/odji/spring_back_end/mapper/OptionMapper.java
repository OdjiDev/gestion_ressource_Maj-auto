package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.OptionDto;
import com.odji.spring_back_end.model.Option;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OptionMapper {

    public OptionDto toDto(Option entity) {
        if (entity == null) {
            return null;
        }

        return OptionDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .build();
    }

    public List<OptionDto> toDtoList(List<Option> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Option toEntity(OptionDto dto) {
        if (dto == null) {
            return null;
        }

        Option entity = new Option();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        return entity;
    }
}
