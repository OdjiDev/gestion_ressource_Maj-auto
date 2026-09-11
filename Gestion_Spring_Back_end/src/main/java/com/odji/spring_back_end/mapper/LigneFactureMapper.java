package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.FactureDto;
import com.odji.spring_back_end.dto.LigneFactureDto;
import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.model.Facture;
import com.odji.spring_back_end.model.LigneFacture;
import com.odji.spring_back_end.model.Produit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigneFactureMapper {

    public LigneFactureDto toDto(LigneFacture entity) {
        if (entity == null) {
            return null;
        }
        return LigneFactureDto.builder()
                .id(entity.getId())
                .quantite(entity.getQuantite())
                .date(entity.getDate())
                .produit(toProduitDto(entity.getProduit()))
                .facture(toFactureDto(entity.getFacture()))
                .build();
    }

    public List<LigneFactureDto> toDtoList(List<LigneFacture> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LigneFacture toEntity(LigneFactureDto dto) {
        if (dto == null) {
            return null;
        }
        LigneFacture entity = new LigneFacture();
        entity.setId(dto.getId());
        entity.setQuantite(dto.getQuantite());
        entity.setDate(dto.getDate());
        // Relations attachées dans le SERVICE
        return entity;
    }

    // ==================== Sous-mappers ====================

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

    private FactureDto toFactureDto(Facture facture) {
        if (facture == null) return null;
        return FactureDto.builder()
                .id(facture.getId())
                .numero(facture.getNumero())
                .code(facture.getCode())
                .datecommande(facture.getDatecommande())
                .build();
    }
}
