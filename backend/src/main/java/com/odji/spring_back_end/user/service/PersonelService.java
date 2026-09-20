package com.odji.spring_back_end.user.service;

import com.odji.spring_back_end.user.dto.PersonelDto;
import com.odji.spring_back_end.common.exception.BusinessException;
import com.odji.spring_back_end.common.exception.DuplicateResourceException;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import com.odji.spring_back_end.user.mapper.PersonelMapper;
import com.odji.spring_back_end.option.entity.Option;
import com.odji.spring_back_end.user.entity.Personel;
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
public class PersonelService {

    private final PersonelRepository personelRepository;
    private final OptionRepository optionRepository;
    private final PersonelMapper personelMapper;
    //private final PasswordEncoder passwordEncoder;

    public List<PersonelDto> findAll() {
        return personelMapper.toDtoList(personelRepository.findAll());
    }

    /*
        public Page<PersonelDto> findAll(Pageable pageable) {
            return personelRepository.findAllWithRelations(pageable)
                    .map(personelMapper::toDto);
        }

        public PersonelDto findById(Integer id) {
            Personel entity = personelRepository.findByIdWithRelations(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Personel", id));
            return personelMapper.toDto(entity);
        }

        public PersonelDto findByEmail(String email) {
            Personel entity = personelRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Personel avec email " + email));
            return personelMapper.toDto(entity);
        }

        @Transactional
        public PersonelDto create(PersonelDto dto) {
            log.info("Création personel email={}", dto.getEmail());
            if (personelRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateResourceException("Email " + dto.getEmail() + " déjà utilisé");
            }

            Personel entity = personelMapper.toEntity(dto);
            entity.setId(null);
            // ⚠️ Hash du password OBLIGATOIRE
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
            attachRole(entity, dto);

            return personelMapper.toDto(personelRepository.save(entity));
        }

        @Transactional
        public PersonelDto update(Integer id, PersonelDto dto) {
            Personel existing = personelRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Personel", id));

            existing.setNom(dto.getNom());
         /*   existing.setPrenom(dto.getPrenom());
            existing.setEmail(dto.getEmail());
            existing.setNumero(dto.getNumero());
            existing.setSexe(dto.getSexe());
            existing.setDateDeNaissance(dto.getDateDeNaissance());
            existing.setLieuDeNaissance(dto.getLieuDeNaissance());

            // ⚠️ Password : hash UNIQUEMENT si fourni
            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                existing.setPassword(passwordEncoder.encode(dto.getPassword()));


            }



            attachRole(existing, dto);
            return personelMapper.toDto(existing);
        }
    */
    @Transactional
    public void delete(Integer id) {
        Personel entity = personelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personel", id));
        personelRepository.delete(entity);
    }

    public PersonelDto create(PersonelDto dto) {


   /* private void attachRole(Personel entity, PersonelDto dto) {
        if (dto.getRole() != null && dto.getRole().getId() != null) {
            Option role = optionRepository.findById(dto.getRole().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Option", dto.getRole().getId()));
            entity.setRole(role);
        }
    }


    */
    return null;
    }
}
