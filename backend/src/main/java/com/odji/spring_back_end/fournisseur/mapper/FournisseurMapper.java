package com.odji.spring_back_end.fournisseur.mapper;

import com.odji.spring_back_end.fournisseur.dto.FournisseurDto;
import com.odji.spring_back_end.fournisseur.entity.Fournisseur;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FournisseurMapper {

    public FournisseurDto toDto(Fournisseur entity) {
        if (entity == null) return null;
        return FournisseurDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .adresse(entity.getAdresse())
                .mail(entity.getMail())
                .numtel(entity.getNumtel())
                .build();
    }

    public List<FournisseurDto> toDtoList(List<Fournisseur> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Fournisseur toEntity(FournisseurDto dto) {
        if (dto == null) return null;
        Fournisseur entity = new Fournisseur();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setAdresse(dto.getAdresse());
        entity.setMail(dto.getMail());
        entity.setNumtel(dto.getNumtel());
        return entity;
    }
}
