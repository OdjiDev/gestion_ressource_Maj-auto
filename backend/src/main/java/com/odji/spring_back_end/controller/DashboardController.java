package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.DashboardStats;
import com.odji.spring_back_end.repository.CategorieRepository;
import com.odji.spring_back_end.repository.FactureRepository;
import com.odji.spring_back_end.repository.FournisseurRepository;
import com.odji.spring_back_end.repository.PersonelRepository;
import com.odji.spring_back_end.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final FournisseurRepository fournisseurRepository;
    private final FactureRepository factureRepository;
    private final PersonelRepository personelRepository;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStats> getStats() {
        DashboardStats stats = DashboardStats.builder()
                .totalProduits(produitRepository.count())
                .totalCategories(categorieRepository.count())
                .totalFournisseurs(fournisseurRepository.count())
                .totalFactures(factureRepository.count())
                .totalPersonels(personelRepository.count())
                .produitsEnRupture(produitRepository.findProduitsEnRupture().size())
                .build();

        return ResponseEntity.ok(stats);
    }
}
