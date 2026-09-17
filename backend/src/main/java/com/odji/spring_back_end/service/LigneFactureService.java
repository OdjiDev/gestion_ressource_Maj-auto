package com.odji.spring_back_end.service;

import com.odji.spring_back_end.dto.LigneFactureDto;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.mapper.LigneFactureMapper;
import com.odji.spring_back_end.model.Facture;
import com.odji.spring_back_end.model.LigneFacture;
import com.odji.spring_back_end.model.Produit;
import com.odji.spring_back_end.repository.FactureRepository;
import com.odji.spring_back_end.repository.LigneFactureRepository;
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
public class LigneFactureService {

    private final LigneFactureRepository ligneFactureRepository;
    private final ProduitRepository produitRepository;
    private final FactureRepository factureRepository;
    private final LigneFactureMapper ligneFactureMapper;

    public List<LigneFactureDto> findAll() {
        return ligneFactureMapper.toDtoList(ligneFactureRepository.findAll());
    }

    public Page<LigneFactureDto> findAll(Pageable pageable) {
        return ligneFactureRepository.findAllWithRelations(pageable)
                .map(ligneFactureMapper::toDto);
    }

    public LigneFactureDto findById(Integer id) {
        LigneFacture entity = ligneFactureRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneFacture", id));
        return ligneFactureMapper.toDto(entity);
    }

    public List<LigneFactureDto> findByFacture(Integer idFacture) {
        return ligneFactureMapper.toDtoList(
                ligneFactureRepository.findAllByFactureId(idFacture));
    }

    public List<LigneFactureDto> findByProduit(Integer idProduit) {
        return ligneFactureMapper.toDtoList(
                ligneFactureRepository.findAllByProduitId(idProduit));
    }

    @Transactional
    public LigneFactureDto create(LigneFactureDto dto) {
        LigneFacture entity = ligneFactureMapper.toEntity(dto);
        entity.setId(null);
        attachRelations(entity, dto);
        return ligneFactureMapper.toDto(ligneFactureRepository.save(entity));
    }

    @Transactional
    public LigneFactureDto update(Integer id, LigneFactureDto dto) {
        LigneFacture existing = ligneFactureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneFacture", id));

        existing.setQuantite(dto.getQuantite());
        existing.setDate(dto.getDate());
        attachRelations(existing, dto);

        return ligneFactureMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        LigneFacture entity = ligneFactureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneFacture", id));
        ligneFactureRepository.delete(entity);
    }

    private void attachRelations(LigneFacture entity, LigneFactureDto dto) {
        if (dto.getProduit() != null && dto.getProduit().getId() != null) {
            Produit produit = produitRepository.findById(dto.getProduit().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produit", dto.getProduit().getId()));
            entity.setProduit(produit);
        }
        if (dto.getFacture() != null && dto.getFacture().getId() != null) {
            Facture facture = factureRepository.findById(dto.getFacture().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Facture", dto.getFacture().getId()));
            entity.setFacture(facture);
        }
    }
}
