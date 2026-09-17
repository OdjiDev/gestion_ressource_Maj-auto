package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.LigneReparationDto;
import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.model.LigneReparation;
import com.odji.spring_back_end.model.Produit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigneReparationMapper {

    public LigneReparationDto toDto(LigneReparation entity) {
        if (entity == null) {
            return null;
        }
        return LigneReparationDto.builder()
                .id(entity.getId())
                .quantite(entity.getQuantite())
                .date(entity.getDate())
                .produit(toProduitDto(entity.getProduit()))
                .build();
    }

    public List<LigneReparationDto> toDtoList(List<LigneReparation> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LigneReparation toEntity(LigneReparationDto dto) {
        if (dto == null) {
            return null;
        }
        LigneReparation entity = new LigneReparation();
        entity.setId(dto.getId());
        entity.setQuantite(dto.getQuantite());
        entity.setDate(dto.getDate());
        // Relation produit attachée dans le SERVICE
        return entity;
    }

    private ProduitDto toProduitDto(Produit produit) {
        if (produit == null) return null;
        return ProduitDto.builder()
                .id(produit.getId())
                .codeproduit(produit.getCodeproduit())
                .nom(produit.getNom())
                .designation(produit.getDesignation())
                .quantite(produit.getQuantite())
                .build();
    }
}
