package com.odji.spring_back_end.facture.mapper;

import com.odji.spring_back_end.facture.dto.FactureDto;
import com.odji.spring_back_end.fournisseur.dto.FournisseurDto;
import com.odji.spring_back_end.facture.entity.Facture;
import com.odji.spring_back_end.fournisseur.entity.Fournisseur;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FactureMapper {

    public FactureDto toDto(Facture entity) {
        if (entity == null) return null;
        return FactureDto.builder()
                .id(entity.getId())
                .numero(entity.getNumero())
                .code(entity.getCode())
                .datecommande(entity.getDatecommande())
                .fournisseur(toFournisseurDto(entity.getFournisseur()))
                .build();
    }

    public List<FactureDto> toDtoList(List<Facture> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Facture toEntity(FactureDto dto) {
        if (dto == null) return null;
        Facture entity = new Facture();
        entity.setId(dto.getId());
        entity.setNumero(dto.getNumero());
        entity.setCode(dto.getCode());
        entity.setDatecommande(dto.getDatecommande());
        return entity;
    }

    private FournisseurDto toFournisseurDto(Fournisseur f) {
        if (f == null) return null;
        return FournisseurDto.builder()
                .id(f.getId())
                .nom(f.getNom())
                .prenom(f.getPrenom())
                .mail(f.getMail())
                .build();
    }
}
