package com.odji.spring_back_end.affectation.service;

import com.odji.spring_back_end.affectation.dto.MagasinDto;
import com.odji.spring_back_end.common.exception.BusinessException;
import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.affectation.mapper.MagasinMapper;
import com.odji.spring_back_end.affectation.entity.Magasin;
import com.odji.spring_back_end.affectation.repository.MagasinRepository;
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
public class MagasinService {

    private final MagasinRepository magasinRepository;
    private final ProduitRepository produitRepository;
    private final MagasinMapper magasinMapper;

    public List<MagasinDto> findAll() {
        return magasinMapper.toDtoList(magasinRepository.findAll());
    }

    public Page<MagasinDto> findAll(Pageable pageable) {
        return magasinRepository.findAll(pageable).map(magasinMapper::toDto);
    }

    public MagasinDto findById(Integer id) {
        Magasin entity = magasinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Magasin", id));
        return magasinMapper.toDto(entity);
    }

    public List<MagasinDto> searchByNom(String nom) {
        return magasinMapper.toDtoList(
                magasinRepository.findAllByNomContainingIgnoreCase(nom));
    }

    @Transactional
    public MagasinDto create(MagasinDto dto) {
        log.info("Création magasin nom={}", dto.getNom());
        if (magasinRepository.existsByNom(dto.getNom())) {
            throw new DuplicateResourceException("Magasin " + dto.getNom() + " existe déjà");
        }
        Magasin entity = magasinMapper.toEntity(dto);
        entity.setId(null);
        return magasinMapper.toDto(magasinRepository.save(entity));
    }

    @Transactional
    public MagasinDto update(Integer id, MagasinDto dto) {
        log.info("Mise à jour magasin id={}", id);
        Magasin existing = magasinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Magasin", id));
        existing.setNom(dto.getNom());
        return magasinMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression magasin id={}", id);
        Magasin entity = magasinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Magasin", id));

        long nbProduits = produitRepository.countByMagasinId(id);
        if (nbProduits > 0) {
            throw new BusinessException(
                    "Impossible de supprimer : " + nbProduits + " produit(s) associé(s)");
        }
        magasinRepository.delete(entity);
    }
}
