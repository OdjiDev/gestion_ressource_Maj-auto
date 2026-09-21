package com.odji.spring_back_end.demande.service;

import com.odji.spring_back_end.demande.dto.DemandeDto;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.demande.mapper.DemandeMapper;
import com.odji.spring_back_end.affectation.entity.Bureau;
import com.odji.spring_back_end.demande.entity.Demande;
import com.odji.spring_back_end.affectation.repository.BureauRepository;
import com.odji.spring_back_end.demande.repository.DemandeRepository;
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
public class DemandeService {

    private final DemandeRepository demandeRepository;
    private final BureauRepository bureauRepository;
    private final DemandeMapper demandeMapper;

    // ==================== LECTURE ====================

    public List<DemandeDto> findAll() {
        return demandeMapper.toDtoList(demandeRepository.findAll());
    }

    /*
    public Page<DemandeDto> findAll(Pageable pageable) {
        return demandeRepository.findAllWithRelations(pageable)
                .map(demandeMapper::toDto);
    }
*/
    public DemandeDto findById(Integer id) {
        Demande entity = demandeRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande", id));
        return demandeMapper.toDto(entity);
    }

    public List<DemandeDto> findByBureau(Integer idBureau) {
        return demandeMapper.toDtoList(
                demandeRepository.findAllByBureauId(idBureau));
    }

    public List<DemandeDto> searchByMotif(String motif) {
        return demandeMapper.toDtoList(
                demandeRepository.findAllByMotifContainingIgnoreCase(motif));
    }

    // ==================== ÉCRITURE ====================

    @Transactional
    public DemandeDto create(DemandeDto dto) {
        log.info("Création demande motif={}", dto.getMotif());

        Demande entity = demandeMapper.toEntity(dto);
        entity.setId(null);

        attachBureau(entity, dto);

        Demande saved = demandeRepository.save(entity);
        return demandeMapper.toDto(saved);
    }

    @Transactional
    public DemandeDto update(Integer id, DemandeDto dto) {
        log.info("Mise à jour demande id={}", id);

        Demande existing = demandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande", id));

        existing.setMotif(dto.getMotif());
        attachBureau(existing, dto);

        return demandeMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Suppression demande id={}", id);

        Demande entity = demandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande", id));

        demandeRepository.delete(entity);
    }

    // ==================== Interne ====================

    private void attachBureau(Demande entity, DemandeDto dto) {
        if (dto.getBureau() != null && dto.getBureau().getId() != null) {
            Bureau bureau = bureauRepository.findById(dto.getBureau().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Bureau", dto.getBureau().getId()));
            entity.setBureau(bureau);
        }
    }
}
