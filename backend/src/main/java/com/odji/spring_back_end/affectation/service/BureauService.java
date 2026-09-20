package com.odji.spring_back_end.affectation.service;

import com.odji.spring_back_end.affectation.dto.BureauDto;
import com.odji.spring_back_end.common.exception.BusinessException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.affectation.mapper.BureauMapper;
import com.odji.spring_back_end.affectation.entity.Bureau;
import com.odji.spring_back_end.affectation.entity.Departement;
import com.odji.spring_back_end.affectation.repository.BureauRepository;
import com.odji.spring_back_end.demande.repository.DemandeRepository;
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
public class BureauService {

    private final BureauRepository bureauRepository;
    private final DepartementRepository departementRepository;
    private final DemandeRepository demandeRepository;
    private final BureauMapper bureauMapper;

    public List<BureauDto> findAll() {
        return bureauMapper.toDtoList(bureauRepository.findAll());
    }

    public Page<BureauDto> findAll(Pageable pageable) {
        return bureauRepository.findAll(pageable).map(bureauMapper::toDto);
    }

    public BureauDto findById(Integer id) {
        Bureau entity = bureauRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bureau", id));
        return bureauMapper.toDto(entity);
    }

    public List<BureauDto> searchByNom(String nom) {
        return bureauMapper.toDtoList(
                bureauRepository.findAllByNomContainingIgnoreCase(nom));
    }

    public List<BureauDto> findByDepartement(Integer idDepartement) {
        return bureauMapper.toDtoList(
                bureauRepository.findAllByDepartementId(idDepartement));
    }

    @Transactional
    public BureauDto create(BureauDto dto) {
        log.info("Création bureau nom={}", dto.getNom());
        Bureau entity = bureauMapper.toEntity(dto);
        entity.setId(null);
        attachDepartement(entity, dto);
        return bureauMapper.toDto(bureauRepository.save(entity));
    }

    @Transactional
    public BureauDto update(Integer id, BureauDto dto) {
        log.info("Mise à jour bureau id={}", id);
        Bureau existing = bureauRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bureau", id));
        existing.setNom(dto.getNom());
        attachDepartement(existing, dto);
        return bureauMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression bureau id={}", id);
        Bureau entity = bureauRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bureau", id));

        long nbDemandes = demandeRepository.countByBureauId(id);
        if (nbDemandes > 0) {
            throw new BusinessException(
                    "Impossible de supprimer : " + nbDemandes + " demande(s) associée(s)");
        }
        bureauRepository.delete(entity);
    }

    private void attachDepartement(Bureau entity, BureauDto dto) {
        if (dto.getDepartement() != null && dto.getDepartement().getId() != null) {
            Departement dep = departementRepository.findById(dto.getDepartement().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Departement", dto.getDepartement().getId()));
            entity.setDepartement(dep);
        }
    }
}
