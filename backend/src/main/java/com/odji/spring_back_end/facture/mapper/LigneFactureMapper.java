package com.odji.spring_back_end.facture.mapper;

import com.odji.spring_back_end.facture.dto.FactureDto;
import com.odji.spring_back_end.facture.dto.LigneFactureDto;
import com.odji.spring_back_end.produit.dto.ProduitDto;
import com.odji.spring_back_end.facture.entity.Facture;
import com.odji.spring_back_end.facture.entity.LigneFacture;
import com.odji.spring_back_end.produit.entity.Produit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigneFactureMapper {

    public LigneFactureDto toDto(LigneFacture entity) {
        if (entity == null) return null;
        return LigneFactureDto.builder()
                .id(entity.getId())
                .quantite(entity.getQuantite())
                .date(entity.getDate())
                .produit(toProduitDto(entity.getProduit()))
                .facture(toFactureDto(entity.getFacture()))
                .build();
    }

    public List<LigneFactureDto> toDtoList(List<LigneFacture> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public LigneFacture toEntity(LigneFactureDto dto) {
        if (dto == null) return null;
        LigneFacture entity = new LigneFacture();
        entity.setId(dto.getId());
        entity.setQuantite(dto.getQuantite());
        entity.setDate(dto.getDate());
        return entity;
    }

    private ProduitDto toProduitDto(Produit p) {
        if (p == null) return null;
        return ProduitDto.builder()
                .id(p.getId()).codeproduit(p.getCodeproduit())
                .nom(p.getNom()).quantite(p.getQuantite()).build();
    }

    private FactureDto toFactureDto(Facture f) {
        if (f == null) return null;
        return FactureDto.builder()
                .id(f.getId()).numero(f.getNumero()).code(f.getCode())
                .datecommande(f.getDatecommande()).build();
    }
}
