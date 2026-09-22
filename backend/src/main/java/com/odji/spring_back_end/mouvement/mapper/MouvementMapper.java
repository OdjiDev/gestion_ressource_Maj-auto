package com.odji.spring_back_end.mouvement.mapper;

import com.odji.spring_back_end.mouvement.dto.MouvementCreateRequest;
import com.odji.spring_back_end.mouvement.dto.MouvementDto;
import com.odji.spring_back_end.mouvement.entity.Mouvement;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MouvementMapper {

    @Mapping(target = "compteId", source = "compte.id")
    @Mapping(target = "compteNom", source = "compte.nom")
    MouvementDto toDto(Mouvement entity);

    List<MouvementDto> toDtoList(List<Mouvement> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "compte", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "dateEnregistrement", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "validatedBy", ignore = true)
    @Mapping(target = "validatedAt", ignore = true)
    @Mapping(target = "parentId", ignore = true)
    @Mapping(target = "justificatifPath", ignore = true)
    Mouvement toEntity(MouvementCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "compte", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "dateEnregistrement", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "validatedBy", ignore = true)
    @Mapping(target = "validatedAt", ignore = true)
    @Mapping(target = "parentId", ignore = true)
    @Mapping(target = "justificatifPath", ignore = true)
    void updateEntity(MouvementCreateRequest request, @MappingTarget Mouvement entity);
}
