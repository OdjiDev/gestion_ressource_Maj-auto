package com.odji.spring_back_end.service;

import com.odji.spring_back_end.dto.CategorieDto;
import com.odji.spring_back_end.exception.BusinessException;
import com.odji.spring_back_end.exception.DuplicateResourceException;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.mapper.CategorieMapper;
import com.odji.spring_back_end.model.Categorie;
import com.odji.spring_back_end.repository.CategorieRepository;
import com.odji.spring_back_end.repository.ProduitRepository;
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
public class CategorieService {

    private final CategorieRepository categorieRepository;
    private final ProduitRepository produitRepository;
    private final CategorieMapper categorieMapper;

    // ==================== LECTURE ====================

    public List<CategorieDto> findAll() {
        return categorieMapper.toDtoList(categorieRepository.findAll());
    }

    public Page<CategorieDto> findAll(Pageable pageable) {
        return categorieRepository.findAll(pageable)
                .map(categorieMapper::toDto);
    }

    public CategorieDto findById(Integer id) {
        Categorie entity = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie", id));
        return categorieMapper.toDto(entity);
    }

    public CategorieDto findByCode(String code) {
        Categorie entity = categorieRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categorie avec code " + code));
        return categorieMapper.toDto(entity);
    }

    public List<CategorieDto> searchByNom(String nom) {
        return categorieMapper.toDtoList(
                categorieRepository.findAllByNomContainingIgnoreCase(nom));
    }

    // ==================== ÉCRITURE ====================

    @Transactional
    public CategorieDto create(CategorieDto dto) {
        log.info("Création catégorie code={}", dto.getCode());

        if (categorieRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException(
                    "Une catégorie avec le code " + dto.getCode() + " existe déjà");
        }

        Categorie entity = categorieMapper.toEntity(dto);
        entity.setId(null);

        Categorie saved = categorieRepository.save(entity);
        return categorieMapper.toDto(saved);
    }

    @Transactional
    public CategorieDto update(Integer id, CategorieDto dto) {
        log.info("Mise à jour catégorie id={}", id);

        Categorie existing = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie", id));

        // Vérif unicité du code si changé
        if (dto.getCode() != null
                && !dto.getCode().equals(existing.getCode())
                && categorieRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException(
                    "Une catégorie avec le code " + dto.getCode() + " existe déjà");
        }

        existing.setNom(dto.getNom());                 // ⚠️ adapter
        existing.setCode(dto.getCode());
        existing.setDesignation(dto.getDesignation());

        return categorieMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression catégorie id={}", id);

        Categorie entity = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie", id));

        // Vérif métier : pas de produits associés
        long nbProduits = produitRepository.countByCategorieId(id);
        if (nbProduits > 0) {
            throw new BusinessException(
                    "Impossible de supprimer : " + nbProduits + " produit(s) utilisent cette catégorie");
        }

        categorieRepository.delete(entity);
    }
}
