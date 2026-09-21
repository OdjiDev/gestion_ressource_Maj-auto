package com.odji.spring_back_end.option.service;

import com.odji.spring_back_end.option.dto.OptionDto;
import com.odji.spring_back_end.common.exception.BusinessException;
import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.option.mapper.OptionMapper;
import com.odji.spring_back_end.option.entity.Option;
import com.odji.spring_back_end.option.repository.OptionRepository;
import com.odji.spring_back_end.user.repository.PersonelRepository;
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
public class OptionService {

    private final OptionRepository optionRepository;
    private final PersonelRepository personelRepository;
    private final OptionMapper optionMapper;

    public List<OptionDto> findAll() {
        return optionMapper.toDtoList(optionRepository.findAll());
    }

    public Page<OptionDto> findAll(Pageable pageable) {
        return optionRepository.findAll(pageable).map(optionMapper::toDto);
    }

    public OptionDto findById(Integer id) {
        Option entity = optionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option", id));
        return optionMapper.toDto(entity);
    }

    @Transactional
    public OptionDto create(OptionDto dto) {
        if (optionRepository.existsByNom(dto.getNom())) {
            throw new DuplicateResourceException("Rôle " + dto.getNom() + " existe déjà");
        }
        Option entity = optionMapper.toEntity(dto);
        entity.setId(null);
        return optionMapper.toDto(optionRepository.save(entity));
    }

    @Transactional
    public OptionDto update(Integer id, OptionDto dto) {
        Option existing = optionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option", id));
        existing.setNom(dto.getNom());
        return optionMapper.toDto(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Option entity = optionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option", id));
        long nbPersonels = personelRepository.countByRoleId(id);
        if (nbPersonels > 0) {
            throw new BusinessException(
                    "Impossible de supprimer : " + nbPersonels + " personel(s) associé(s)");
        }
        optionRepository.delete(entity);
    }
}
