package com.odji.spring_back_end.affectation.service;

import com.odji.spring_back_end.affectation.dto.DepartementDto;
import com.odji.spring_back_end.common.exception.BusinessException;
import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.affectation.mapper.DepartementMapper;
import com.odji.spring_back_end.affectation.entity.Departement;
import com.odji.spring_back_end.affectation.repository.BureauRepository;
import com.odji.spring_back_end.affectation.repository.DepartementRepository;
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
public class DepartementService {

    private final DepartementRepository departementRepository;
    private final BureauRepository bureauRepository;
    private final DepartementMapper departementMapper;

    // ==================== LECTURE ====================

    public List<DepartementDto> findAll() {
        return departementMapper.toDtoList(departementRepository.findAll());
    }

    public Page<DepartementDto> findAll(Pageable pageable) {
        return departementRepository.findAll(pageable)
                .map(departementMapper::toDto);
    }

    public DepartementDto findById(Integer id) {
        Departement entity = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departement", id));
        return departementMapper.toDto(entity);
    }

    public DepartementDto findByCode(String code) {
        Departement entity = departementRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departement avec code " + code));
        return departementMapper.toDto(entity);
    }

    public List<DepartementDto> searchByNom(String nom) {
        return departementMapper.toDtoList(
                departementRepository.findAllByNomContainingIgnoreCase(nom));
    }

    // ==================== ÉCRITURE ====================

    @Transactional
    public DepartementDto create(DepartementDto dto) {
        log.info("Création département code={}", dto.getCode());

        if (dto.getCode() != null && departementRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException(
                    "Un département avec le code " + dto.getCode() + " existe déjà");
        }

        Departement entity = departementMapper.toEntity(dto);
        entity.setId(null);

        Departement saved = departementRepository.save(entity);
        return departementMapper.toDto(saved);
    }

    @Transactional
    public DepartementDto update(Integer id, DepartementDto dto) {
        log.info("Mise à jour département id={}", id);

        Departement existing = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departement", id));

        // Vérif unicité du code si changé
        if (dto.getCode() != null
                && !dto.getCode().equals(existing.getCode())
                && departementRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException(
                    "Un département avec le code " + dto.getCode() + " existe déjà");
        }

        existing.setNom(dto.getNom());
        existing.setCode(dto.getCode());

        return departementMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression département id={}", id);

        Departement entity = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departement", id));

        // Vérif métier : pas de bureaux associés
        long nbBureaux = bureauRepository.countByDepartementId(id);
        if (nbBureaux > 0) {
            throw new BusinessException(
                    "Impossible de supprimer : " + nbBureaux + " bureau(x) associé(s)");
        }

        departementRepository.delete(entity);
    }
}
