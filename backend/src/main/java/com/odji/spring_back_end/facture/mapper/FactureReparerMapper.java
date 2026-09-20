package com.odji.spring_back_end.facture.mapper;

import com.odji.spring_back_end.facture.dto.FactureReparerDto;
import com.odji.spring_back_end.societe.dto.SocieteDto;
import com.odji.spring_back_end.facture.entity.FactureReparer;
import com.odji.spring_back_end.societe.entity.Societe;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FactureReparerMapper {

    public FactureReparerDto toDto(FactureReparer entity) {
        if (entity == null) return null;
        return FactureReparerDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .date(entity.getDate())
                .societe(toSocieteDto(entity.getSociete()))
                .build();
    }

    public List<FactureReparerDto> toDtoList(List<FactureReparer> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public FactureReparer toEntity(FactureReparerDto dto) {
        if (dto == null) return null;
        FactureReparer entity = new FactureReparer();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setDate(dto.getDate());
        return entity;
    }

    private SocieteDto toSocieteDto(Societe s) {
        if (s == null) return null;
        return SocieteDto.builder()
                .id(s.getId())
                .nom(s.getNom())
                .numerofiscal(s.getNumerofiscal())
                .build();
    }
}
