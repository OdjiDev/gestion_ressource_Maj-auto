package com.odji.spring_back_end.service;

import com.odji.spring_back_end.dto.SignalerDto;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.mapper.SignalerMapper;
import com.odji.spring_back_end.model.Personel;
import com.odji.spring_back_end.model.Produit;
import com.odji.spring_back_end.model.Signaler;
import com.odji.spring_back_end.repository.PersonelRepository;
import com.odji.spring_back_end.repository.ProduitRepository;
import com.odji.spring_back_end.repository.SignalerRepository;
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
public class SignalerService {

    private final SignalerRepository signalerRepository;
    private final ProduitRepository produitRepository;
    private final PersonelRepository personelRepository;
    private final SignalerMapper signalerMapper;

    public List<SignalerDto> findAll() {
        return signalerMapper.toDtoList(signalerRepository.findAll());
    }

    public Page<SignalerDto> findAll(Pageable pageable) {
        return signalerRepository.findAllWithRelations(pageable)
                .map(signalerMapper::toDto);
    }

    public SignalerDto findById(Integer id) {
        Signaler entity = signalerRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Signaler", id));
        return signalerMapper.toDto(entity);
    }

    public List<SignalerDto> findByEtat(String etat) {
        return signalerMapper.toDtoList(signalerRepository.findAllByEtat(etat));
    }

    @Transactional
    public SignalerDto create(SignalerDto dto) {
        Signaler entity = signalerMapper.toEntity(dto);
        entity.setId(null);
        attachRelations(entity, dto);
        return signalerMapper.toDto(signalerRepository.save(entity));
    }

    @Transactional
    public SignalerDto update(Integer id, SignalerDto dto) {
        Signaler existing = signalerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Signaler", id));
        existing.setEtat(dto.getEtat());
        attachRelations(existing, dto);
        return signalerMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Signaler entity = signalerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Signaler", id));
        signalerRepository.delete(entity);
    }

    private void attachRelations(Signaler entity, SignalerDto dto) {
        if (dto.getProduit() != null && dto.getProduit().getId() != null) {
            Produit produit = produitRepository.findById(dto.getProduit().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produit", dto.getProduit().getId()));
            entity.setProduit(produit);
        }
        if (dto.getPersonel() != null && dto.getPersonel().getId() != null) {
            Personel personel = personelRepository.findById(dto.getPersonel().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Personel", dto.getPersonel().getId()));
            entity.setPersonel(personel);
        }
    }
}
