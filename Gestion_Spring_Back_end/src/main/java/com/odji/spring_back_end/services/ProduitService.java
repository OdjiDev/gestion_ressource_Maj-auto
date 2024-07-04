package com.odji.spring_back_end.services;

import com.odji.spring_back_end.dto.MagasinDto;
import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.model.Magasin;
import com.odji.spring_back_end.model.Produit;
import com.odji.spring_back_end.repository.CategorieRepository;
import com.odji.spring_back_end.repository.MagasinRepository;
import com.odji.spring_back_end.services.CategorieService;
import com.odji.spring_back_end.services.MagasinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class ProduitService {

        private final CategorieService categorieService;
        private final MagasinService magasinService;

        public ProduitService(CategorieService categorieService, MagasinService magasinService) {
            this.categorieService = categorieService;
            this.magasinService = magasinService;
        }

                // You can add other service dependencies for LigneFactureDto, etc. if needed

        public List<ProduitDto> produitDtoList(List<Produit> produits) {
            return produits.stream()
                    .map(this::produitToDto) // Utilise la méthode de conversion individuelle
                    .collect(Collectors.toList());
        }
        public ProduitDto produitToDto(Produit produit) {
            if (produit == null) {
                return null;
            }

            ProduitDto produitDto = new ProduitDto();
            produitDto.setId(produit.getId());
            produitDto.setCodeproduit(produit.getCodeproduit());
            produitDto.setNom(produit.getNom());
            produitDto.setDesignation(produit.getDesignation());
            produitDto.setQuantite(produit.getQuantite());
            produitDto.setPrixAchat(produit.getPrixAchat());
            produitDto.setCategorieDto(categorieService.categorieToDto(produit.getCategorie()));
           // produitDto.setMagasinDto(magasinService.magasinToDto(produit.getMagasin()));

            return produitDto;
        }

        public Produit dtoToProduit(ProduitDto produitDto) {
            if (produitDto == null) {
                return null;
            }

            Produit produit = new Produit();
            produit.setId(produitDto.getId());
            produit.setCodeproduit(produitDto.getCodeproduit());
            produit.setNom(produitDto.getNom());
            produit.setDesignation(produitDto.getDesignation());
            produit.setQuantite(produitDto.getQuantite());
            produit.setPrixAchat(produitDto.getPrixAchat());
            produit.setCategorie(categorieService.dtoToCategorie(produitDto.getCategorieDto()));
           // produit.setMagasin(magasinService.dtoToMagasin(produitDto.getMagasinDto())); // Assuming magasinService exists
            // Similar logic for other properties (avarie, lignefacture, etc.)
            return produit;
        }


        // You can add other methods specific to ProduitService here
    }








