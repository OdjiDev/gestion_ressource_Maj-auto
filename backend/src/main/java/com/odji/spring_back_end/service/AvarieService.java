package com.odji.spring_back_end.service;

import com.odji.spring_back_end.dto.AvarieDto;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.mapper.AvarieMapper;
import com.odji.spring_back_end.model.Avarie;
import com.odji.spring_back_end.model.Produit;
import com.odji.spring_back_end.repository.AvarieRepository;
import com.odji.spring_back_end.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AvarieService {

    private final AvarieRepository avarieRepository;
    private final ProduitRepository produitRepository;
    private final AvarieMapper avarieMapper;

    // ==================== LECTURE ====================

    public List<AvarieDto> findAll() {
        return avarieMapper.toDtoList(avarieRepository.findAll());
    }

    public Page<AvarieDto> findAll(Pageable pageable) {
        return avarieRepository.findAllWithRelations(pageable)
                .map(avarieMapper::toDto);
    }

    public AvarieDto findById(Integer id) {
        Avarie entity = avarieRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avarie", id));
        return avarieMapper.toDto(entity);
    }

    public List<AvarieDto> findByProduit(Integer idProduit) {
        return avarieMapper.toDtoList(
                avarieRepository.findAllByProduitId(idProduit));
    }

    public List<AvarieDto> findByDate(LocalDate debut, LocalDate fin) {
        return avarieMapper.toDtoList(
                avarieRepository.findAllByDateBetween(debut, fin));
    }

    public List<AvarieDto> searchByMotif(String motif) {
        return avarieMapper.toDtoList(
                avarieRepository.findAllByMotifContainingIgnoreCase(motif));
    }

    // ==================== ÉCRITURE ====================

    @Transactional
    public AvarieDto create(AvarieDto dto) {
        log.info("Création avarie produit={}",
                dto.getProduit() != null ? dto.getProduit().getId() : null);

        Avarie entity = avarieMapper.toEntity(dto);
        entity.setId(null);

        attachProduit(entity, dto);

        Avarie saved = avarieRepository.save(entity);
        return avarieMapper.toDto(saved);
    }

    @Transactional
    public AvarieDto update(Integer id, AvarieDto dto) {
        log.info("Mise à jour avarie id={}", id);

        Avarie existing = avarieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avarie", id));

        existing.setQuantite(dto.getQuantite());
        existing.setDate(dto.getDate());
        existing.setMotif(dto.getMotif());

        attachProduit(existing, dto);

        return avarieMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression avarie id={}", id);
        Avarie entity = avarieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avarie", id));
        avarieRepository.delete(entity);
    }

    // ==================== Interne ====================

    private void attachProduit(Avarie entity, AvarieDto dto) {
        if (dto.getProduit() != null && dto.getProduit().getId() != null) {
            Produit produit = produitRepository.findById(dto.getProduit().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produit", dto.getProduit().getId()));
            entity.setProduit(produit);
        }
    }
}
