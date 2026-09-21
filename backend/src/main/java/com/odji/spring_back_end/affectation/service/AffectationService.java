package com.odji.spring_back_end.affectation.service;

import com.odji.spring_back_end.affectation.dto.AffectationDto;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.affectation.mapper.AffectationMapper;
import com.odji.spring_back_end.affectation.entity.Affectation;
import com.odji.spring_back_end.user.entity.Personel;
import com.odji.spring_back_end.produit.entity.Produit;
import com.odji.spring_back_end.affectation.repository.AffectationRepository;
import com.odji.spring_back_end.user.repository.PersonelRepository;
import com.odji.spring_back_end.produit.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AffectationService {

    private final AffectationRepository affectationRepository;
    private final ProduitRepository produitRepository;
    private final PersonelRepository personelRepository;
    private final AffectationMapper affectationMapper;

    // ==================== LECTURE ====================

    public List<AffectationDto> findAll() {
        return affectationMapper.toDtoList(affectationRepository.findAll());
    }

    public Page<AffectationDto> findAll(Pageable pageable) {
        return affectationRepository.findAllWithRelations(pageable)
                .map(affectationMapper::toDto);
    }

    public AffectationDto findById(Integer id) {
        Affectation entity = affectationRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affectation", id));
        return affectationMapper.toDto(entity);
    }

    public List<AffectationDto> findByProduit(Integer idProduit) {
        return affectationMapper.toDtoList(
                affectationRepository.findAllByProduitId(idProduit));
    }

    public List<AffectationDto> findByPersonel(Integer idPersonel) {
        return affectationMapper.toDtoList(
                affectationRepository.findAllByPersonelId(idPersonel));
    }

    public List<AffectationDto> findByDate(LocalDate debut, LocalDate fin) {
        return affectationMapper.toDtoList(
                affectationRepository.findAllByDateBetween(debut, fin));
    }

    // ==================== ÉCRITURE ====================

    @Transactional
    public AffectationDto create(AffectationDto dto) {
        log.info("Création affectation produit={} personel={}",
                dto.getProduit() != null ? dto.getProduit().getId() : null,
                dto.getPersonel() != null ? dto.getPersonel().getId() : null);

        Affectation entity = affectationMapper.toEntity(dto);
        entity.setId(null);   // sécurité : pas d'ID fourni par le client

        // Chargement des relations depuis la BDD
        attachRelations(entity, dto);

        Affectation saved = affectationRepository.save(entity);
        return affectationMapper.toDto(saved);
    }

    @Transactional
    public AffectationDto update(Integer id, AffectationDto dto) {
        log.info("Mise à jour affectation id={}", id);

        Affectation existing = affectationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affectation", id));

        existing.setQuantite(dto.getQuantite());
        existing.setDate(dto.getDate());
        existing.setMotif(dto.getMotif());

        attachRelations(existing, dto);

        return affectationMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression affectation id={}", id);

        Affectation entity = affectationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affectation", id));

        affectationRepository.delete(entity);
    }

    // ==================== Interne ====================

    private void attachRelations(Affectation entity, AffectationDto dto) {
        if (dto.getProduit() != null && dto.getProduit().getId() != null) {
            Produit produit = produitRepository.findById(dto.getProduit().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produit", dto.getProduit().getId()));
            entity.setProduit(produit);
        }

        if (dto.getPersonel() != null && dto.getPersonel().getId() != null) {
            Personel personel = personelRepository.findById(dto.getPersonel().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Personel", dto.getPersonel().getId()));
            entity.setPersonel(personel);
        }
    }
}
