package com.odji.spring_back_end.service;

import com.odji.spring_back_end.dto.FactureDto;
import com.odji.spring_back_end.exception.DuplicateResourceException;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.mapper.FactureMapper;
import com.odji.spring_back_end.model.Facture;
import com.odji.spring_back_end.model.Fournisseur;
import com.odji.spring_back_end.repository.FactureRepository;
import com.odji.spring_back_end.repository.FournisseurRepository;
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
public class FactureService {

    private final FactureRepository factureRepository;
    private final FournisseurRepository fournisseurRepository;
    private final FactureMapper factureMapper;

    public List<FactureDto> findAll() {
        return factureMapper.toDtoList(factureRepository.findAll());
    }

    public Page<FactureDto> findAll(Pageable pageable) {
        return factureRepository.findAllWithRelations(pageable).map(factureMapper::toDto);
    }

    public FactureDto findById(Integer id) {
        Facture entity = factureRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture", id));
        return factureMapper.toDto(entity);
    }

    public FactureDto findByNumero(String numero) {
        Facture entity = factureRepository.findByNumero(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Facture numero " + numero));
        return factureMapper.toDto(entity);
    }

    @Transactional
    public FactureDto create(FactureDto dto) {
        if (dto.getNumero() != null && factureRepository.existsByNumero(dto.getNumero())) {
            throw new DuplicateResourceException("Numéro facture déjà utilisé");
        }
        Facture entity = factureMapper.toEntity(dto);
        entity.setId(null);
        attachFournisseur(entity, dto);
        return factureMapper.toDto(factureRepository.save(entity));
    }

    @Transactional
    public FactureDto update(Integer id, FactureDto dto) {
        Facture existing = factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture", id));
        existing.setNumero(dto.getNumero());
        existing.setCode(dto.getCode());
        existing.setDatecommande(dto.getDatecommande());
        attachFournisseur(existing, dto);
        return factureMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Facture entity = factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture", id));
        factureRepository.delete(entity);
    }

    private void attachFournisseur(Facture entity, FactureDto dto) {
        if (dto.getFournisseur() != null && dto.getFournisseur().getId() != null) {
            Fournisseur f = fournisseurRepository.findById(dto.getFournisseur().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Fournisseur", dto.getFournisseur().getId()));
            entity.setFournisseur(f);
        }
    }
}
