package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.CategorieDto;
import com.odji.spring_back_end.dto.MagasinDto;
import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.model.Categorie;
import com.odji.spring_back_end.model.Magasin;
import com.odji.spring_back_end.model.Produit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProduitMapper {

    // ==================== Entité → DTO ====================

    public ProduitDto toDto(Produit entity) {
        if (entity == null) {
            return null;
        }

        return ProduitDto.builder()
                .id(entity.getId())
                .codeproduit(entity.getCodeproduit())
                .nom(entity.getNom())
                .designation(entity.getDesignation())
                .quantite(entity.getQuantite())
                .categorie(toCategorieDto(entity.getCategorie()))
                .magasin(toMagasinDto(entity.getMagasin()))
                .build();
    }

    public List<ProduitDto> toDtoList(List<Produit> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ==================== DTO → Entité ====================

    public Produit toEntity(ProduitDto dto) {
        if (dto == null) {
            return null;
        }

        Produit entity = new Produit();
        entity.setId(dto.getId());
        entity.setCodeproduit(dto.getCodeproduit());
        entity.setNom(dto.getNom());
        entity.setDesignation(dto.getDesignation());
        entity.setQuantite(dto.getQuantite());
        // ⚠️ Les relations (categorie, magasin) sont gérées dans le SERVICE
        //    car elles nécessitent un accès au repository (charger par ID).
        return entity;
    }

    // ==================== Sous-mappers (statiques ou via MapStruct plus tard) ====================

    private CategorieDto toCategorieDto(Categorie categorie) {
        if (categorie == null) {
            return null;
        }
        return CategorieDto.builder()
                .id(categorie.getId())
                .nom(categorie.getNom())
                .build();
    }

    private MagasinDto toMagasinDto(Magasin magasin) {
        if (magasin == null) {
            return null;
        }
        return MagasinDto.builder()
                .id(magasin.getId())
                .nom(magasin.getNom())
                .build();
    }
}
