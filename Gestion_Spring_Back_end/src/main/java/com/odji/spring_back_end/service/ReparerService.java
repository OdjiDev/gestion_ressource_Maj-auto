package com.odji.spring_back_end.service;

import com.odji.spring_back_end.dto.ReparerDto;
import com.odji.spring_back_end.exception.ResourceNotFoundException;
import com.odji.spring_back_end.mapper.ReparerMapper;
import com.odji.spring_back_end.model.Reparer;
import com.odji.spring_back_end.repository.ReparerRepository;
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
public class ReparerService {

    private final ReparerRepository reparerRepository;
    private final ReparerMapper reparerMapper;

    public List<ReparerDto> findAll() {
        return reparerMapper.toDtoList(reparerRepository.findAll());
    }

    public Page<ReparerDto> findAll(Pageable pageable) {
        return reparerRepository.findAll(pageable).map(reparerMapper::toDto);
    }

    public ReparerDto findById(Integer id) {
        Reparer entity = reparerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reparer", id));
        return reparerMapper.toDto(entity);
    }

    public List<ReparerDto> findByDateRange(LocalDate debut, LocalDate fin) {
        return reparerMapper.toDtoList(reparerRepository.findAllByDateBetween(debut, fin));
    }

    @Transactional
    public ReparerDto create(ReparerDto dto) {
        Reparer entity = reparerMapper.toEntity(dto);
        entity.setId(null);
        return reparerMapper.toDto(reparerRepository.save(entity));
    }

    @Transactional
    public ReparerDto update(Integer id, ReparerDto dto) {
        Reparer existing = reparerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reparer", id));
        existing.setMotif(dto.getMotif());
        existing.setDate(dto.getDate());
        return reparerMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Reparer entity = reparerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reparer", id));
        reparerRepository.delete(entity);
    }
}
