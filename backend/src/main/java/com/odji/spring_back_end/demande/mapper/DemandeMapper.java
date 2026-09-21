package com.odji.spring_back_end.demande.mapper;

import com.odji.spring_back_end.affectation.dto.BureauDto;
import com.odji.spring_back_end.demande.dto.DemandeDto;
import com.odji.spring_back_end.demande.dto.LigneDemandeDto;
import com.odji.spring_back_end.affectation.entity.Bureau;
import com.odji.spring_back_end.demande.entity.Demande;
import com.odji.spring_back_end.demande.entity.LigneDemande;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DemandeMapper {

    // ==================== Entité → DTO ====================

    public DemandeDto toDto(Demande entity) {
        if (entity == null) {
            return null;
        }
        return DemandeDto.builder()
                .id(entity.getId())
                .motif(entity.getMotif())
                .bureau(toBureauDto(entity.getBureau()))
                .lignesDemande(toLigneDemandeDtoList(entity.getLignesDemande()))
                .build();
    }

    public List<DemandeDto> toDtoList(List<Demande> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ==================== DTO → Entité ====================

    public Demande toEntity(DemandeDto dto) {
        if (dto == null) {
            return null;
        }
        Demande entity = new Demande();
        entity.setId(dto.getId());
        entity.setMotif(dto.getMotif());
        // bureau + lignesDemande attachés dans le SERVICE
        return entity;
    }

    // ==================== Sous-mappers ====================

    private BureauDto toBureauDto(Bureau bureau) {
        if (bureau == null) return null;
        return BureauDto.builder()
                .id(bureau.getId())
                .nom(bureau.getNom())
                .build();
    }

    private LigneDemandeDto toLigneDemandeDto(LigneDemande ligne) {
        if (ligne == null) return null;
        return LigneDemandeDto.builder()
                .id(ligne.getId())
                .quantite(ligne.getQuantite())
                .date(ligne.getDate())
                // produit : si besoin, ajoute un sous-mapper
                .build();
    }

    private List<LigneDemandeDto> toLigneDemandeDtoList(List<LigneDemande> lignes) {
        if (lignes == null || lignes.isEmpty()) return Collections.emptyList();
        return lignes.stream()
                .map(this::toLigneDemandeDto)
                .collect(Collectors.toList());
    }
}
