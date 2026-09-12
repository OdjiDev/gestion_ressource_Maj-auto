package com.odji.spring_back_end.service;

import com.odji.spring_back_end.dto.LigneReparationDto;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.mapper.LigneReparationMapper;
import com.odji.spring_back_end.model.LigneReparation;
import com.odji.spring_back_end.model.Produit;
import com.odji.spring_back_end.model.Reparer;
import com.odji.spring_back_end.repository.LigneReparationRepository;
import com.odji.spring_back_end.repository.ProduitRepository;
import com.odji.spring_back_end.repository.ReparerRepository;
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
public class LigneReparationService {

    private final LigneReparationRepository ligneReparationRepository;
    private final ProduitRepository produitRepository;
    private final ReparerRepository reparerRepository;
    private final LigneReparationMapper ligneReparationMapper;

    public List<LigneReparationDto> findAll() {
        return ligneReparationMapper.toDtoList(ligneReparationRepository.findAll());
    }

    public Page<LigneReparationDto> findAll(Pageable pageable) {
        return ligneReparationRepository.findAllWithRelations(pageable)
                .map(ligneReparationMapper::toDto);
    }

    public LigneReparationDto findById(Integer id) {
        LigneReparation entity = ligneReparationRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneReparation", id));
        return ligneReparationMapper.toDto(entity);
    }

    public List<LigneReparationDto> findByReparer(Integer idReparer) {
        return ligneReparationMapper.toDtoList(
                ligneReparationRepository.findAllByReparerId(idReparer));
    }

    public List<LigneReparationDto> findByProduit(Integer idProduit) {
        return ligneReparationMapper.toDtoList(
                ligneReparationRepository.findAllByProduitId(idProduit));
    }

    @Transactional
    public LigneReparationDto create(LigneReparationDto dto) {
        LigneReparation entity = ligneReparationMapper.toEntity(dto);
        entity.setId(null);
        attachRelations(entity, dto);
        return ligneReparationMapper.toDto(ligneReparationRepository.save(entity));
    }

    @Transactional
    public LigneReparationDto update(Integer id, LigneReparationDto dto) {
        LigneReparation existing = ligneReparationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneReparation", id));

        existing.setQuantite(dto.getQuantite());
        existing.setDate(dto.getDate());
        attachRelations(existing, dto);

        return ligneReparationMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        LigneReparation entity = ligneReparationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneReparation", id));
        ligneReparationRepository.delete(entity);
    }

    private void attachRelations(LigneReparation entity, LigneReparationDto dto) {
        if (dto.getProduit() != null && dto.getProduit().getId() != null) {
            Produit produit = produitRepository.findById(dto.getProduit().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produit", dto.getProduit().getId()));
            entity.setProduit(produit);
        }
        if (dto.getReparer() != null && dto.getReparer().getId() != null) {
            Reparer reparer = reparerRepository.findById(dto.getReparer().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Reparer", dto.getReparer().getId()));
            entity.setReparer(reparer);
        }
    }
}
