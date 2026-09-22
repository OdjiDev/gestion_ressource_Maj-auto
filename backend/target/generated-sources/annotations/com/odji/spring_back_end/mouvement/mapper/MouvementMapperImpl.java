package com.odji.spring_back_end.mouvement.mapper;

import com.odji.spring_back_end.compte.entity.Compte;
import com.odji.spring_back_end.mouvement.dto.MouvementCreateRequest;
import com.odji.spring_back_end.mouvement.dto.MouvementDto;
import com.odji.spring_back_end.mouvement.entity.Mouvement;
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
public class MouvementMapperImpl implements MouvementMapper {

    @Override
    public MouvementDto toDto(Mouvement entity) {
        if ( entity == null ) {
            return null;
        }

        MouvementDto.MouvementDtoBuilder mouvementDto = MouvementDto.builder();

        mouvementDto.compteId( entityCompteId( entity ) );
        mouvementDto.compteNom( entityCompteNom( entity ) );
        mouvementDto.id( entity.getId() );
        mouvementDto.dateOperation( entity.getDateOperation() );
        mouvementDto.dateEnregistrement( entity.getDateEnregistrement() );
        mouvementDto.type( entity.getType() );
        mouvementDto.statut( entity.getStatut() );
        mouvementDto.montant( entity.getMontant() );
        mouvementDto.devise( entity.getDevise() );
        mouvementDto.motif( entity.getMotif() );
        mouvementDto.categorie( entity.getCategorie() );
        mouvementDto.modePaiement( entity.getModePaiement() );
        mouvementDto.reference( entity.getReference() );
        mouvementDto.justificatifPath( entity.getJustificatifPath() );
        mouvementDto.factureId( entity.getFactureId() );
        mouvementDto.parentId( entity.getParentId() );
        mouvementDto.validatedBy( entity.getValidatedBy() );
        mouvementDto.validatedAt( entity.getValidatedAt() );

        return mouvementDto.build();
    }

    @Override
    public List<MouvementDto> toDtoList(List<Mouvement> entities) {
        if ( entities == null ) {
            return null;
        }

        List<MouvementDto> list = new ArrayList<MouvementDto>( entities.size() );
        for ( Mouvement mouvement : entities ) {
            list.add( toDto( mouvement ) );
        }

        return list;
    }

    @Override
    public Mouvement toEntity(MouvementCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Mouvement.MouvementBuilder mouvement = Mouvement.builder();

        mouvement.dateOperation( request.getDateOperation() );
        mouvement.type( request.getType() );
        mouvement.montant( request.getMontant() );
        mouvement.devise( request.getDevise() );
        mouvement.motif( request.getMotif() );
        mouvement.categorie( request.getCategorie() );
        mouvement.modePaiement( request.getModePaiement() );
        mouvement.reference( request.getReference() );
        mouvement.factureId( request.getFactureId() );

        return mouvement.build();
    }

    @Override
    public void updateEntity(MouvementCreateRequest request, Mouvement entity) {
        if ( request == null ) {
            return;
        }

        entity.setDateOperation( request.getDateOperation() );
        entity.setType( request.getType() );
        entity.setMontant( request.getMontant() );
        entity.setDevise( request.getDevise() );
        entity.setMotif( request.getMotif() );
        entity.setCategorie( request.getCategorie() );
        entity.setModePaiement( request.getModePaiement() );
        entity.setReference( request.getReference() );
        entity.setFactureId( request.getFactureId() );
    }

    private Long entityCompteId(Mouvement mouvement) {
        if ( mouvement == null ) {
            return null;
        }
        Compte compte = mouvement.getCompte();
        if ( compte == null ) {
            return null;
        }
        Long id = compte.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCompteNom(Mouvement mouvement) {
        if ( mouvement == null ) {
            return null;
        }
        Compte compte = mouvement.getCompte();
        if ( compte == null ) {
            return null;
        }
        String nom = compte.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }
}
