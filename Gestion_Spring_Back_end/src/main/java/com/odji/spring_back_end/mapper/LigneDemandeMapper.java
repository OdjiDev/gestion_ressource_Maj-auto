package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.LigneDemandeDto;
import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.model.LigneDemande;
import com.odji.spring_back_end.model.Produit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigneDemandeMapper {

    public LigneDemandeDto toDto(LigneDemande entity) {
        if (entity == null) {
            return null;
        }
        return LigneDemandeDto.builder()
                .id(entity.getId())
                .quantite(entity.getQuantite())
                .date(entity.getDate())
                .produit(toProduitDto(entity.getProduit()))
                .build();
    }

    public List<LigneDemandeDto> toDtoList(List<LigneDemande> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LigneDemande toEntity(LigneDemandeDto dto) {
        if (dto == null) {
            return null;
        }
        LigneDemande entity = new LigneDemande();
        entity.setId(dto.getId());
        entity.setQuantite(dto.getQuantite());
        entity.setDate(dto.getDate());
        // Le produit est attaché dans le SERVICE
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
