package com.odji.spring_back_end.demande.service;

import com.odji.spring_back_end.demande.dto.LigneDemandeDto;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.demande.mapper.LigneDemandeMapper;
import com.odji.spring_back_end.demande.entity.LigneDemande;
import com.odji.spring_back_end.produit.entity.Produit;
import com.odji.spring_back_end.demande.repository.LigneDemandeRepository;
import com.odji.spring_back_end.produit.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LigneDemandeService {

    private final LigneDemandeRepository ligneDemandeRepository;
    private final ProduitRepository produitRepository;
    private final LigneDemandeMapper ligneDemandeMapper;

    public List<LigneDemandeDto> findAll() {
        return ligneDemandeMapper.toDtoList(ligneDemandeRepository.findAll());
    }

    public Page<LigneDemandeDto> findAll(Pageable pageable) {
        return ligneDemandeRepository.findAllWithRelations(pageable)
                .map(ligneDemandeMapper::toDto);
    }

    public LigneDemandeDto findById(Integer id) {
        LigneDemande entity = ligneDemandeRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneDemande", id));
        return ligneDemandeMapper.toDto(entity);
    }

    public List<LigneDemandeDto> findByDemande(Integer idDemande) {
        return ligneDemandeMapper.toDtoList(
                ligneDemandeRepository.findAllByDemandeId(idDemande));
    }

    public List<LigneDemandeDto> findByProduit(Integer idProduit) {
        return ligneDemandeMapper.toDtoList(
                ligneDemandeRepository.findAllByProduitId(idProduit));
    }

    @Transactional
    public LigneDemandeDto create(LigneDemandeDto dto) {
        log.info("Création ligne demande produit={}",
                dto.getProduit() != null ? dto.getProduit().getId() : null);

        LigneDemande entity = ligneDemandeMapper.toEntity(dto);
        entity.setId(null);
        attachProduit(entity, dto);

        LigneDemande saved = ligneDemandeRepository.save(entity);
        return ligneDemandeMapper.toDto(saved);
    }

    @Transactional
    public LigneDemandeDto update(Integer id, LigneDemandeDto dto) {
        log.info("Mise à jour ligne demande id={}", id);

        LigneDemande existing = ligneDemandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneDemande", id));

        existing.setQuantite(dto.getQuantite());
        existing.setDate(dto.getDate());
        attachProduit(existing, dto);

        return ligneDemandeMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression ligne demande id={}", id);
        LigneDemande entity = ligneDemandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneDemande", id));
        ligneDemandeRepository.delete(entity);
    }

    private void attachProduit(LigneDemande entity, LigneDemandeDto dto) {
        if (dto.getProduit() != null && dto.getProduit().getId() != null) {
            Produit produit = produitRepository.findById(dto.getProduit().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produit", dto.getProduit().getId()));
            entity.setProduit(produit);
        }
    }
}
