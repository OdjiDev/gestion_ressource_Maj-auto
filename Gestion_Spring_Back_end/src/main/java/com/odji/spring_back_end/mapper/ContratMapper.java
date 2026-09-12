package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.ContratDto;
import com.odji.spring_back_end.dto.SocieteDto;
import com.odji.spring_back_end.model.Contrat;
import com.odji.spring_back_end.model.Societe;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContratMapper {

    public ContratDto toDto(Contrat entity) {
        if (entity == null) return null;
        return ContratDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .datedebut(entity.getDatedebut())
                .datedefin(entity.getDatedefin())
                .societe(toSocieteDto(entity.getSociete()))
                .build();
    }

    public List<ContratDto> toDtoList(List<Contrat> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Contrat toEntity(ContratDto dto) {
        if (dto == null) return null;
        Contrat entity = new Contrat();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setDatedebut(dto.getDatedebut());
        entity.setDatedefin(dto.getDatedefin());
        return entity;
    }

    private SocieteDto toSocieteDto(Societe societe) {
        if (societe == null) return null;
        return SocieteDto.builder()
                .id(societe.getId())
                .nom(societe.getNom())
                .numerofiscal(societe.getNumerofiscal())
                .build();
    }
}
