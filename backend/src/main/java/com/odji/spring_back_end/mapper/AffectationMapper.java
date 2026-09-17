package com.odji.spring_back_end.mapper;

import com.odji.spring_back_end.dto.AffectationDto;
import com.odji.spring_back_end.dto.PersonelDto;
import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.model.Affectation;
import com.odji.spring_back_end.model.Personel;
import com.odji.spring_back_end.model.Produit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AffectationMapper {

    // ==================== Entité → DTO ====================

    public AffectationDto toDto(Affectation entity) {
        if (entity == null) {
            return null;
        }

        return AffectationDto.builder()
                .id(entity.getId())
                .quantite(entity.getQuantite())
                .date(entity.getDate())
                .motif(entity.getMotif())
                .produit(toProduitDto(entity.getProduit()))
                .personel(toPersonelDto(entity.getPersonel()))
                .build();
    }

    public List<AffectationDto> toDtoList(List<Affectation> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ==================== DTO → Entité (champs scalaires seulement) ====================

    public Affectation toEntity(AffectationDto dto) {
        if (dto == null) {
            return null;
        }

        Affectation entity = new Affectation();
        entity.setId(dto.getId());
        entity.setQuantite(dto.getQuantite());
        entity.setDate(dto.getDate());
        entity.setMotif(dto.getMotif());
        // ⚠️ Les relations (produit, personel) sont gérées dans le SERVICE
        //    car elles nécessitent un chargement depuis la BDD
        return entity;
    }

    // ==================== Sous-mappers (à extraire idéalement) ====================

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

    private PersonelDto toPersonelDto(Personel personel) {
        if (personel == null) return null;
        return PersonelDto.builder()
                .id(personel.getId())
                .nom(personel.getNom())
                // ⚠️ adapte selon les vrais champs de Personel
                .build();
    }
}
