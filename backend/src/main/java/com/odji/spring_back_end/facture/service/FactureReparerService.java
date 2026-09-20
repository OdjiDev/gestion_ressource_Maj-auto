package com.odji.spring_back_end.facture.service;

import com.odji.spring_back_end.facture.dto.FactureReparerDto;
import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.facture.mapper.FactureReparerMapper;
import com.odji.spring_back_end.facture.entity.FactureReparer;
import com.odji.spring_back_end.societe.entity.Societe;
import com.odji.spring_back_end.facture.repository.FactureReparerRepository;
import com.odji.spring_back_end.societe.repository.SocieteRepository;
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
public class FactureReparerService {

    private final FactureReparerRepository factureReparerRepository;
    private final SocieteRepository societeRepository;
    private final FactureReparerMapper factureReparerMapper;

    public List<FactureReparerDto> findAll() {
        return factureReparerMapper.toDtoList(factureReparerRepository.findAll());
    }

    public Page<FactureReparerDto> findAll(Pageable pageable) {
        return factureReparerRepository.findAllWithRelations(pageable)
                .map(factureReparerMapper::toDto);
    }

    public FactureReparerDto findById(Integer id) {
        FactureReparer entity = factureReparerRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("FactureReparer", id));
        return factureReparerMapper.toDto(entity);
    }

    @Transactional
    public FactureReparerDto create(FactureReparerDto dto) {
        if (dto.getCode() != null && factureReparerRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException("Code facture réparer déjà utilisé");
        }
        FactureReparer entity = factureReparerMapper.toEntity(dto);
        entity.setId(null);
        attachSociete(entity, dto);
        return factureReparerMapper.toDto(factureReparerRepository.save(entity));
    }

    @Transactional
    public FactureReparerDto update(Integer id, FactureReparerDto dto) {
        FactureReparer existing = factureReparerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FactureReparer", id));
        existing.setCode(dto.getCode());
        existing.setDate(dto.getDate());
        attachSociete(existing, dto);
        return factureReparerMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        FactureReparer entity = factureReparerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FactureReparer", id));
        factureReparerRepository.delete(entity);
    }

    private void attachSociete(FactureReparer entity, FactureReparerDto dto) {
        if (dto.getSociete() != null && dto.getSociete().getId() != null) {
            Societe s = societeRepository.findById(dto.getSociete().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Societe", dto.getSociete().getId()));
            entity.setSociete(s);
        }
    }
}
