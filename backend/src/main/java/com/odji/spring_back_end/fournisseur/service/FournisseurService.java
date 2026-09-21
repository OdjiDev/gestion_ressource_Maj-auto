package com.odji.spring_back_end.fournisseur.service;

import com.odji.spring_back_end.fournisseur.dto.FournisseurDto;
import com.odji.spring_back_end.common.exception.BusinessException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.fournisseur.mapper.FournisseurMapper;
import com.odji.spring_back_end.fournisseur.entity.Fournisseur;
import com.odji.spring_back_end.facture.repository.FactureRepository;
import com.odji.spring_back_end.fournisseur.repository.FournisseurRepository;
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
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final FactureRepository factureRepository;
    private final FournisseurMapper fournisseurMapper;

    public List<FournisseurDto> findAll() {
        return fournisseurMapper.toDtoList(fournisseurRepository.findAll());
    }

    public Page<FournisseurDto> findAll(Pageable pageable) {
        return fournisseurRepository.findAll(pageable).map(fournisseurMapper::toDto);
    }

    public FournisseurDto findById(Integer id) {
        Fournisseur entity = fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur", id));
        return fournisseurMapper.toDto(entity);
    }

    public List<FournisseurDto> searchByNom(String nom) {
        return fournisseurMapper.toDtoList(
                fournisseurRepository.findAllByNomContainingIgnoreCase(nom));
    }

    @Transactional
    public FournisseurDto create(FournisseurDto dto) {
        Fournisseur entity = fournisseurMapper.toEntity(dto);
        entity.setId(null);
        return fournisseurMapper.toDto(fournisseurRepository.save(entity));
    }

    @Transactional
    public FournisseurDto update(Integer id, FournisseurDto dto) {
        Fournisseur existing = fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur", id));
        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setAdresse(dto.getAdresse());
        existing.setMail(dto.getMail());
        existing.setNumtel(dto.getNumtel());
        return fournisseurMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Fournisseur entity = fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur", id));
        long nbFactures = factureRepository.countByFournisseurId(id);
        if (nbFactures > 0) {
            throw new BusinessException("Impossible de supprimer : " + nbFactures + " facture(s)");
        }
        fournisseurRepository.delete(entity);
    }
}
