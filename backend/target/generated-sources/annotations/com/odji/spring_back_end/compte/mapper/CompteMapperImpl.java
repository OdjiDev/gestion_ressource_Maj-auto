package com.odji.spring_back_end.compte.mapper;

import com.odji.spring_back_end.compte.dto.CompteCreateRequest;
import com.odji.spring_back_end.compte.dto.CompteDto;
import com.odji.spring_back_end.compte.entity.Compte;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-21T17:50:11+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class CompteMapperImpl implements CompteMapper {

    @Override
    public CompteDto toDto(Compte entity) {
        if ( entity == null ) {
            return null;
        }

        CompteDto.CompteDtoBuilder compteDto = CompteDto.builder();

        compteDto.id( entity.getId() );
        compteDto.code( entity.getCode() );
        compteDto.nom( entity.getNom() );
        compteDto.type( entity.getType() );
        compteDto.soldeInitial( entity.getSoldeInitial() );
        compteDto.devise( entity.getDevise() );
        compteDto.seuilAlerte( entity.getSeuilAlerte() );
        compteDto.actif( entity.getActif() );

        return compteDto.build();
    }

    @Override
    public List<CompteDto> toDtoList(List<Compte> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CompteDto> list = new ArrayList<CompteDto>( entities.size() );
        for ( Compte compte : entities ) {
            list.add( toDto( compte ) );
        }

        return list;
    }

    @Override
    public Compte toEntity(CompteCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Compte.CompteBuilder compte = Compte.builder();

        compte.code( request.getCode() );
        compte.nom( request.getNom() );
        compte.type( request.getType() );
        compte.soldeInitial( request.getSoldeInitial() );
        compte.devise( request.getDevise() );
        compte.seuilAlerte( request.getSeuilAlerte() );
        compte.actif( request.getActif() );

        return compte.build();
    }

    @Override
    public void updateEntity(CompteCreateRequest request, Compte entity) {
        if ( request == null ) {
            return;
        }

        entity.setCode( request.getCode() );
        entity.setNom( request.getNom() );
        entity.setType( request.getType() );
        entity.setSoldeInitial( request.getSoldeInitial() );
        entity.setDevise( request.getDevise() );
        entity.setSeuilAlerte( request.getSeuilAlerte() );
        entity.setActif( request.getActif() );
    }
}
