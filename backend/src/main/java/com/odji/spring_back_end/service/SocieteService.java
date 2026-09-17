package com.odji.spring_back_end.service;

import com.odji.spring_back_end.dto.SocieteDto;
import com.odji.spring_back_end.exception.BusinessException;
import com.odji.spring_back_end.exception.DuplicateResourceException;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.mapper.SocieteMapper;
import com.odji.spring_back_end.model.Societe;
import com.odji.spring_back_end.repository.FactureReparerRepository;
import com.odji.spring_back_end.repository.SocieteRepository;
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
public class SocieteService {

    private final SocieteRepository societeRepository;
    private final FactureReparerRepository factureReparerRepository;
    private final SocieteMapper societeMapper;

    public List<SocieteDto> findAll() {
        return societeMapper.toDtoList(societeRepository.findAll());
    }

    public Page<SocieteDto> findAll(Pageable pageable) {
        return societeRepository.findAll(pageable).map(societeMapper::toDto);
    }

    public SocieteDto findById(Integer id) {
        Societe entity = societeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Societe", id));
        return societeMapper.toDto(entity);
    }

    public List<SocieteDto> searchByNom(String nom) {
        return societeMapper.toDtoList(
                societeRepository.findAllByNomContainingIgnoreCase(nom));
    }

    @Transactional
    public SocieteDto create(SocieteDto dto) {
        if (dto.getNumerofiscal() != null
                && societeRepository.existsByNumerofiscal(dto.getNumerofiscal())) {
            throw new DuplicateResourceException("Numéro fiscal déjà utilisé");
        }
        Societe entity = societeMapper.toEntity(dto);
        entity.setId(null);
        return societeMapper.toDto(societeRepository.save(entity));
    }

    @Transactional
    public SocieteDto update(Integer id, SocieteDto dto) {
        Societe existing = societeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Societe", id));
        existing.setNom(dto.getNom());
        existing.setAdresse(dto.getAdresse());
        existing.setNumerofiscal(dto.getNumerofiscal());
        return societeMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Societe entity = societeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Societe", id));
        long nbFactures = factureReparerRepository.countBySocieteId(id);
        if (nbFactures > 0) {
            throw new BusinessException(
                    "Impossible de supprimer : " + nbFactures + " facture(s) associée(s)");
        }
        societeRepository.delete(entity);
    }
}
