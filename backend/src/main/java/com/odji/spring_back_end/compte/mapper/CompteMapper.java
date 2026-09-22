package com.odji.spring_back_end.compte.mapper;

import com.odji.spring_back_end.compte.dto.CompteCreateRequest;
import com.odji.spring_back_end.compte.dto.CompteDto;
import com.odji.spring_back_end.compte.entity.Compte;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompteMapper {

    CompteDto toDto(Compte entity);

    List<CompteDto> toDtoList(List<Compte> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Compte toEntity(CompteCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateEntity(CompteCreateRequest request, @MappingTarget Compte entity);
}
