package com.odji.spring_back_end.avarie.mapper;

import com.odji.spring_back_end.avarie.dto.AvarieDto;
import com.odji.spring_back_end.produit.dto.ProduitDto;
import com.odji.spring_back_end.avarie.entity.Avarie;
import com.odji.spring_back_end.produit.entity.Produit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvarieMapper {

    // ==================== Entité → DTO ====================

    public AvarieDto toDto(Avarie entity) {
        if (entity == null) {
            return null;
        }
        return AvarieDto.builder()
                .id(entity.getId())
                .quantite(entity.getQuantite())
                .date(entity.getDate())
                .motif(entity.getMotif())
                .produit(toProduitDto(entity.getProduit()))
                .build();
    }

    public List<AvarieDto> toDtoList(List<Avarie> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ==================== DTO → Entité (champs scalaires seulement) ====================

    public Avarie toEntity(AvarieDto dto) {
        if (dto == null) {
            return null;
        }
        Avarie entity = new Avarie();
        entity.setId(dto.getId());
        entity.setQuantite(dto.getQuantite());
        entity.setDate(dto.getDate());
        entity.setMotif(dto.getMotif());
        // ⚠️ Le produit est géré dans le SERVICE
        return entity;
    }

    // ==================== Sous-mapper ====================

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
