package com.odji.spring_back_end.avarie.mapper;

import com.odji.spring_back_end.avarie.dto.ReparerDto;
import com.odji.spring_back_end.avarie.entity.Reparer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReparerMapper {

    public ReparerDto toDto(Reparer entity) {
        if (entity == null) {
            return null;
        }

        return ReparerDto.builder()
                .id(entity.getId())
                .motif(entity.getMotif())
                .date(entity.getDate())
                .build();
    }

    public List<ReparerDto> toDtoList(List<Reparer> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Reparer toEntity(ReparerDto dto) {
        if (dto == null) {
            return null;
        }

        Reparer entity = new Reparer();
        entity.setId(dto.getId());
        entity.setMotif(dto.getMotif());
        entity.setDate(dto.getDate());
        return entity;
    }
}
