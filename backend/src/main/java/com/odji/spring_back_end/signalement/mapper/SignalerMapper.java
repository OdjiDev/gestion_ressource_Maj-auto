package com.odji.spring_back_end.signalement.mapper;

import com.odji.spring_back_end.user.dto.PersonelDto;
import com.odji.spring_back_end.produit.dto.ProduitDto;
import com.odji.spring_back_end.signalement.dto.SignalerDto;
import com.odji.spring_back_end.user.entity.Personel;
import com.odji.spring_back_end.produit.entity.Produit;
import com.odji.spring_back_end.signalement.entity.Signaler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SignalerMapper {

    // ==================== Entité → DTO ====================

    public SignalerDto toDto(Signaler entity) {
        if (entity == null) {
            return null;
        }

        return SignalerDto.builder()
                .id(entity.getId())
                .etat(entity.getEtat())
                .produit(toProduitDto(entity.getProduit()))
                .personel(toPersonelDto(entity.getPersonel()))
                .build();
    }

    public List<SignalerDto> toDtoList(List<Signaler> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ==================== DTO → Entité ====================

    public Signaler toEntity(SignalerDto dto) {
        if (dto == null) {
            return null;
        }

        Signaler entity = new Signaler();
        entity.setId(dto.getId());
        entity.setEtat(dto.getEtat());
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

    private PersonelDto toPersonelDto(Personel personel) {
        if (personel == null) return null;
        return PersonelDto.builder()
                .id(personel.getId())
                .nom(personel.getNom())
              //  .prenom(personel.getPrenom())
              //  .email(personel.getEmail())
              //  .numero(personel.getNumero())
                // ⚠️ Pas de password !
                .build();
    }
}
