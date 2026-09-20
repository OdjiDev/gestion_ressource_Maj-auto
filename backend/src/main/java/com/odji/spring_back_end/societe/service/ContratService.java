package com.odji.spring_back_end.societe.service;

import com.odji.spring_back_end.societe.dto.ContratDto;
import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.societe.mapper.ContratMapper;
import com.odji.spring_back_end.societe.entity.Contrat;
import com.odji.spring_back_end.societe.entity.Societe;
import com.odji.spring_back_end.societe.repository.ContratRepository;
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
public class ContratService {

    private final ContratRepository contratRepository;
    private final SocieteRepository societeRepository;
    private final ContratMapper contratMapper;

    public List<ContratDto> findAll() {
        return contratMapper.toDtoList(contratRepository.findAll());
    }

    public Page<ContratDto> findAll(Pageable pageable) {
        return contratRepository.findAll(pageable).map(contratMapper::toDto);
    }

    public ContratDto findById(Integer id) {
        Contrat entity = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        return contratMapper.toDto(entity);
    }

    @Transactional
    public ContratDto create(ContratDto dto) {
        log.info("Création contrat code={}", dto.getCode());
        if (dto.getCode() != null && contratRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException("Contrat avec code " + dto.getCode() + " existe déjà");
        }
        Contrat entity = contratMapper.toEntity(dto);
        entity.setId(null);
        attachSociete(entity, dto);
        return contratMapper.toDto(contratRepository.save(entity));
    }

    @Transactional
    public ContratDto update(Integer id, ContratDto dto) {
        Contrat existing = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        existing.setCode(dto.getCode());
        existing.setDatedebut(dto.getDatedebut());
        existing.setDatedefin(dto.getDatedefin());
        attachSociete(existing, dto);
        return contratMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Contrat entity = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        contratRepository.delete(entity);
    }

    private void attachSociete(Contrat entity, ContratDto dto) {
        if (dto.getSociete() != null && dto.getSociete().getId() != null) {
            Societe societe = societeRepository.findById(dto.getSociete().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Societe", dto.getSociete().getId()));
            entity.setSociete(societe);
        }
    }
}
