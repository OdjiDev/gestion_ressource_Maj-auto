package com.odji.spring_back_end.facture.service;

import com.odji.spring_back_end.facture.dto.LigneFactureReparerDto;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.facture.mapper.LigneFactureReparerMapper;
import com.odji.spring_back_end.facture.entity.FactureReparer;
import com.odji.spring_back_end.facture.entity.LigneFactureReparer;
import com.odji.spring_back_end.produit.entity.Produit;
import com.odji.spring_back_end.avarie.entity.Reparer;
import com.odji.spring_back_end.facture.repository.FactureReparerRepository;
import com.odji.spring_back_end.facture.repository.LigneFactureReparerRepository;
import com.odji.spring_back_end.produit.repository.ProduitRepository;
import com.odji.spring_back_end.avarie.repository.ReparerRepository;
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
public class LigneFactureReparerService {

    private final LigneFactureReparerRepository ligneFactureReparerRepository;
    private final ProduitRepository produitRepository;
    private final ReparerRepository reparerRepository;
    private final FactureReparerRepository factureReparerRepository;
    private final LigneFactureReparerMapper ligneFactureReparerMapper;

    public List<LigneFactureReparerDto> findAll() {
        return ligneFactureReparerMapper.toDtoList(
                ligneFactureReparerRepository.findAll());
    }

    public Page<LigneFactureReparerDto> findAll(Pageable pageable) {
        return ligneFactureReparerRepository.findAllWithRelations(pageable)
                .map(ligneFactureReparerMapper::toDto);
    }

    public LigneFactureReparerDto findById(Integer id) {
        LigneFactureReparer entity = ligneFactureReparerRepository
                .findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneFactureReparer", id));
        return ligneFactureReparerMapper.toDto(entity);
    }

    public List<LigneFactureReparerDto> findByFactureReparer(Integer idFactureReparer) {
        return ligneFactureReparerMapper.toDtoList(
                ligneFactureReparerRepository.findAllByFactureReparerId(idFactureReparer));
    }

    public List<LigneFactureReparerDto> findByProduit(Integer idProduit) {
        return ligneFactureReparerMapper.toDtoList(
                ligneFactureReparerRepository.findAllByProduitId(idProduit));
    }

    @Transactional
    public LigneFactureReparerDto create(LigneFactureReparerDto dto) {
        log.info("Création ligne facture réparer");
        LigneFactureReparer entity = ligneFactureReparerMapper.toEntity(dto);
        entity.setId(null);
        attachRelations(entity, dto);
        return ligneFactureReparerMapper.toDto(
                ligneFactureReparerRepository.save(entity));
    }

    @Transactional
    public LigneFactureReparerDto update(Integer id, LigneFactureReparerDto dto) {
        LigneFactureReparer existing = ligneFactureReparerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneFactureReparer", id));

        existing.setQuantite(dto.getQuantite());
        existing.setDate(dto.getDate());
        attachRelations(existing, dto);

        return ligneFactureReparerMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        LigneFactureReparer entity = ligneFactureReparerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneFactureReparer", id));
        ligneFactureReparerRepository.delete(entity);
    }

    private void attachRelations(LigneFactureReparer entity, LigneFactureReparerDto dto) {
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
        if (dto.getFactureReparer() != null && dto.getFactureReparer().getId() != null) {
            FactureReparer fr = factureReparerRepository.findById(dto.getFactureReparer().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "FactureReparer", dto.getFactureReparer().getId()));
            entity.setFactureReparer(fr);
        }
    }
}
