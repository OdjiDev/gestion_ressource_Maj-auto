package com.odji.spring_back_end.mouvement.service;

import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.compte.entity.Compte;
import com.odji.spring_back_end.compte.repository.CompteRepository;
import com.odji.spring_back_end.mouvement.dto.MouvementCreateRequest;
import com.odji.spring_back_end.mouvement.dto.MouvementDto;
import com.odji.spring_back_end.mouvement.dto.SoldeDto;
import com.odji.spring_back_end.mouvement.entity.Mouvement;
import com.odji.spring_back_end.mouvement.enums.StatutMouvement;
import com.odji.spring_back_end.mouvement.enums.TypeMouvement;
import com.odji.spring_back_end.mouvement.mapper.MouvementMapper;
import com.odji.spring_back_end.mouvement.repository.MouvementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MouvementService {

    private final MouvementRepository repository;
    private final CompteRepository compteRepository;
    private final MouvementMapper mapper;

    public List<MouvementDto> findAll() {
        return mapper.toDtoList(repository.findAllActive());
    }

    public List<MouvementDto> findByCompte(Long compteId) {
        return mapper.toDtoList(repository.findByCompteId(compteId));
    }

    public MouvementDto findById(Long id) {
        Mouvement entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mouvement", id));
        return mapper.toDto(entity);
    }

    public SoldeDto getSolde(Long compteId) {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new ResourceNotFoundException("Compte", compteId));

        BigDecimal entrees = repository.sumByCompteAndType(compteId, TypeMouvement.ENTREE);
        BigDecimal sorties = repository.sumByCompteAndType(compteId, TypeMouvement.SORTIE);
        BigDecimal soldeActuel = compte.getSoldeInitial().add(entrees).subtract(sorties);

        return SoldeDto.builder()
                .compteId(compte.getId())
                .compteNom(compte.getNom())
                .soldeInitial(compte.getSoldeInitial())
                .totalEntrees(entrees)
                .totalSorties(sorties)
                .soldeActuel(soldeActuel)
                .devise(compte.getDevise())
                .build();
    }

    @Transactional
    public MouvementDto create(MouvementCreateRequest request) {
        Compte compte = compteRepository.findById(request.getCompteId())
                .orElseThrow(() -> new ResourceNotFoundException("Compte", request.getCompteId()));

        Mouvement entity = mapper.toEntity(request);
        entity.setCompte(compte);
        entity.setStatut(StatutMouvement.VALIDE);
        entity.setDateEnregistrement(LocalDateTime.now());
        entity.setDeleted(false);
        if (entity.getDevise() == null || entity.getDevise().isBlank()) {
            entity.setDevise(compte.getDevise());
        }

        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public MouvementDto update(Long id, MouvementCreateRequest request) {
        Mouvement entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mouvement", id));

        Compte compte = compteRepository.findById(request.getCompteId())
                .orElseThrow(() -> new ResourceNotFoundException("Compte", request.getCompteId()));

        mapper.updateEntity(request, entity);
        entity.setCompte(compte);

        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        Mouvement entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mouvement", id));
        entity.setDeleted(true);
        repository.save(entity);
    }
}
