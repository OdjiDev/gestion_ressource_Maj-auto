package com.odji.spring_back_end.services;

import com.odji.spring_back_end.dto.AvarieDto;
import com.odji.spring_back_end.dto.FournisseurDto;
import com.odji.spring_back_end.model.Avarie;
import com.odji.spring_back_end.model.Fournisseur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FournisseurService {

    public List<FournisseurDto> fournisseurDtoList(List<Fournisseur> fournisseur) {
        return fournisseur.stream()
                .map(this::fournisseurToDto) //utilise la methode de conversion individuel
                .collect(Collectors.toList());
    }


    public FournisseurDto fournisseurToDto(Fournisseur fournisseur) {
        FournisseurDto fournisseurDto = new FournisseurDto();
        fournisseurDto.setId(fournisseur.getId());
        fournisseurDto.setNom(fournisseur.getNom());
        fournisseurDto.setPrenom(fournisseur.getPrenom()); // Assuming typo is corrected (prenom instead of prenom)
        fournisseurDto.setAdresse(fournisseur.getAdresse());
        fournisseurDto.setMail(fournisseur.getMail());
        fournisseurDto.setNumtel(fournisseur.getNumtel());

        return  fournisseurDto;
    }

    public static Fournisseur dtoToFournisseur(FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setAdresse(fournisseurDto.getAdresse());
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setNumtel(fournisseurDto.getNumtel());

        return fournisseur;
    }


}