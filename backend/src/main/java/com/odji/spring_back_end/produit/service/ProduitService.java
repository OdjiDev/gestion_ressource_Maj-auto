package com.odji.spring_back_end.produit.service;   // ⚠️ singulier

import com.odji.spring_back_end.produit.dto.ProduitDto;
import com.odji.spring_back_end.common.exception.BusinessException;
import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.produit.mapper.ProduitMapper;
import com.odji.spring_back_end.categorie.entity.Categorie;
import com.odji.spring_back_end.affectation.entity.Magasin;
import com.odji.spring_back_end.produit.entity.Produit;
import com.odji.spring_back_end.categorie.repository.CategorieRepository;
import com.odji.spring_back_end.affectation.repository.MagasinRepository;
import com.odji.spring_back_end.produit.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final MagasinRepository magasinRepository;
    private final ProduitMapper produitMapper;

    // ==================== LECTURE ====================

    public List<ProduitDto> findAll() {
        return produitMapper.toDtoList(produitRepository.findAll());
    }

    public Page<ProduitDto> findAll(Pageable pageable) {
        return produitRepository.findAllWithRelations(pageable)
                .map(produitMapper::toDto);
    }

    public ProduitDto findById(Integer id) {
        Produit produit = produitRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", id));
        return produitMapper.toDto(produit);
    }

    public ProduitDto findByCodeproduit(String codeproduit) {
        Produit produit = produitRepository.findByCodeproduit(codeproduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit avec code " + codeproduit));
        return produitMapper.toDto(produit);
    }

    public List<ProduitDto> findByCategorie(Integer idCategorie) {
        return produitMapper.toDtoList(produitRepository.findAllByCategorieId(idCategorie));
    }

    public List<ProduitDto> findByMagasin(Integer idMagasin) {
        return produitMapper.toDtoList(produitRepository.findAllByMagasinId(idMagasin));
    }

    public List<ProduitDto> searchByNom(String nom) {
        return produitMapper.toDtoList(produitRepository.findByNomContainingIgnoreCase(nom));
    }

    public List<ProduitDto> findProduitsEnRupture() {
        return produitMapper.toDtoList(produitRepository.findProduitsEnRupture());
    }

    public List<ProduitDto> findProduitsSousSeuil(BigDecimal seuil) {
        return produitMapper.toDtoList(produitRepository.findProduitsSousSeuil(seuil));
    }

    // ==================== ÉCRITURE ====================

    @Transactional
    public ProduitDto create(ProduitDto dto) {
        log.info("Création produit codeproduit={}", dto.getCodeproduit());

        // 1. Validations métier
        if (produitRepository.existsByCodeproduit(dto.getCodeproduit())) {
            throw new DuplicateResourceException(
                    "Un produit avec le code " + dto.getCodeproduit() + " existe déjà");
        }

        // 2. Construction de l'entité
        Produit produit = produitMapper.toEntity(dto);
        produit.setId(null); // sécurise : on ne veut pas d'ID fourni par le client

        // 3. Chargement des relations (depuis la BDD, pas depuis le DTO)
        attachRelations(produit, dto);

        // 4. Sauvegarde
        Produit saved = produitRepository.save(produit);
        return produitMapper.toDto(saved);
    }

    @Transactional
    public ProduitDto update(Integer id, ProduitDto dto) {
        log.info("Mise à jour produit id={}", id);

        Produit existing = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", id));

        // Vérif unicité si le code a changé
        if (dto.getCodeproduit() != null
                && !dto.getCodeproduit().equals(existing.getCodeproduit())
                && produitRepository.existsByCodeproduit(dto.getCodeproduit())) {
            throw new DuplicateResourceException(
                    "Un produit avec le code " + dto.getCodeproduit() + " existe déjà");
        }

        // Mise à jour des champs scalaires
        existing.setCodeproduit(dto.getCodeproduit());
        existing.setNom(dto.getNom());
        existing.setDesignation(dto.getDesignation());
        existing.setQuantite(dto.getQuantite());

        // Mise à jour des relations
        attachRelations(existing, dto);

        // Pas besoin de save() : @Transactional + entité managée → dirty checking
        return produitMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression produit id={}", id);

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", id));

        // ⚠️ Vérifications métier avant suppression
        if (!produit.getAvaries().isEmpty()) {
            throw new BusinessException(
                    "Impossible de supprimer : ce produit a des avaries associées");
        }
        if (!produit.getLignesFacture().isEmpty()) {
            throw new BusinessException(
                    "Impossible de supprimer : ce produit est utilisé dans des factures");
        }

        produitRepository.delete(produit);
    }

    // ==================== Méthodes internes ====================

    /**
     * Attache les relations Categorie et Magasin à partir des IDs fournis dans le DTO.
     * On ne fait JAMAIS confiance au DTO pour créer une Categorie : on la charge depuis la BDD.
     */
    private void attachRelations(Produit produit, ProduitDto dto) {
        if (dto.getCategorie() != null && dto.getCategorie().getId() != null) {
            Categorie categorie = categorieRepository.findById(dto.getCategorie().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Categorie", dto.getCategorie().getId()));
            produit.setCategorie(categorie);
        }

        if (dto.getMagasin() != null && dto.getMagasin().getId() != null) {
            Magasin magasin = magasinRepository.findById(dto.getMagasin().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Magasin", dto.getMagasin().getId()));
            produit.setMagasin(magasin);
        }
    }
}
