package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.FactureReparerDto;
import com.odji.spring_back_end.dto.LigneFactureReparerDto;
import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.dto.ReparerDto;
import com.odji.spring_back_end.model.FactureReparer;
import com.odji.spring_back_end.model.LigneFactureReparer;
import com.odji.spring_back_end.model.Produit;
import com.odji.spring_back_end.model.Reparer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigneFactureReparerMapper {

    public LigneFactureReparerDto toDto(LigneFactureReparer entity) {
        if (entity == null) {
            return null;
        }
        return LigneFactureReparerDto.builder()
                .id(entity.getId())
                .quantite(entity.getQuantite())
                .date(entity.getDate())
                .produit(toProduitDto(entity.getProduit()))
                .reparer(toReparerDto(entity.getReparer()))
                .factureReparer(toFactureReparerDto(entity.getFactureReparer()))
                .build();
    }

    public List<LigneFactureReparerDto> toDtoList(List<LigneFactureReparer> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LigneFactureReparer toEntity(LigneFactureReparerDto dto) {
        if (dto == null) {
            return null;
        }
        LigneFactureReparer entity = new LigneFactureReparer();
        entity.setId(dto.getId());
        entity.setQuantite(dto.getQuantite());
        entity.setDate(dto.getDate());
        // ⚠️ Les relations (produit, reparer, factureReparer) sont gérées dans le SERVICE
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

    private ReparerDto toReparerDto(Reparer reparer) {
        if (reparer == null) return null;
        return ReparerDto.builder()
                .id(reparer.getId())
                // ⚠️ adapte selon les vrais champs de Reparer
                .build();
    }

    private FactureReparerDto toFactureReparerDto(FactureReparer factureReparer) {
        if (factureReparer == null) return null;
        return FactureReparerDto.builder()
                .id(factureReparer.getId())
                .code(factureReparer.getCode())
                .date(factureReparer.getDate())
                .build();
    }
}
