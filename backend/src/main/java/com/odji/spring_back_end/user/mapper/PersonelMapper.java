package com.odji.spring_back_end.user.mapper;

import com.odji.spring_back_end.option.dto.OptionDto;
import com.odji.spring_back_end.user.dto.PersonelDto;
import com.odji.spring_back_end.option.entity.Option;
import com.odji.spring_back_end.user.entity.Personel;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonelMapper {

    // ==================== Entité → DTO ====================

    public PersonelDto toDto(Personel entity) {
        if (entity == null) {
            return null;
        }

        return PersonelDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
               /* .prenom(entity.getPrenom())
                .dateDeNaissance(entity.getDateDeNaissance())
                .lieuDeNaissance(entity.getLieuDeNaissance())
                .sexe(entity.getSexe())
                .numero(entity.getNumero())
                .email(entity.getEmail())       */
                // ⚠️ NE JAMAIS mapper le password vers le DTO sortant !
              //  .role(toOptionDto(entity.getRole()))
                .build();
    }

    public List<PersonelDto> toDtoList(List<Personel> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ==================== DTO → Entité ====================

    public Personel toEntity(PersonelDto dto) {
        if (dto == null) {
            return null;
        }

        Personel entity = new Personel();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
      /*  entity.setPrenom(dto.getPrenom());
        entity.setDateDeNaissance(dto.getDateDeNaissance());
        entity.setLieuDeNaissance(dto.getLieuDeNaissance());
        entity.setSexe(dto.getSexe());
        entity.setNumero(dto.getNumero());
        entity.setEmail(dto.getEmail());        */
        // ⚠️ Le password est hashé dans le SERVICE (BCrypt)
        // ⚠️ Le role est attaché dans le SERVICE
        return entity;
    }

    // ==================== Sous-mapper ====================

    private OptionDto toOptionDto(Option option) {
        if (option == null) {
            return null;
        }
        return OptionDto.builder()
                .id(option.getId())
                .nom(option.getNom())
                .build();
    }
}
