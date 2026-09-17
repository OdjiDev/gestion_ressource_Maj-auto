package com.odji.spring_back_end.controller;

import com.odji.spring_back_end.dto.ProduitDto;
import com.odji.spring_back_end.service.ProduitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    // ==================== CRÉATION ====================

    /**
     * POST /api/produits
     * 201 Created + Location header vers la ressource créée.
     */
    @PostMapping
    public ResponseEntity<ProduitDto> create(
            @Valid @RequestBody ProduitDto dto,
            UriComponentsBuilder uriBuilder) {

        ProduitDto created = produitService.create(dto);

        URI location = uriBuilder.path("/api/produits/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    // ==================== LECTURE ====================

    /**
     * GET /api/produits?page=0&size=20&sort=nom,asc
     * Liste paginée.
     */
    @GetMapping
    public ResponseEntity<Page<ProduitDto>> findAll(
            @PageableDefault(size = 20, sort = "nom", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return ResponseEntity.ok(produitService.findAll(pageable));
    }

    /**
     * GET /api/produits/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProduitDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(produitService.findById(id));
    }

    /**
     * GET /api/produits/code/{codeproduit}
     */
    @GetMapping("/code/{codeproduit}")
    public ResponseEntity<ProduitDto> findByCodeproduit(@PathVariable String codeproduit) {
        return ResponseEntity.ok(produitService.findByCodeproduit(codeproduit));
    }

    /**
     * GET /api/produits/search?nom=vis
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProduitDto>> search(@RequestParam String nom) {
        return ResponseEntity.ok(produitService.searchByNom(nom));
    }

    /**
     * GET /api/produits/categorie/{idCategorie}
     */
    @GetMapping("/categorie/{idCategorie}")
    public ResponseEntity<List<ProduitDto>> findByCategorie(@PathVariable Integer idCategorie) {
        return ResponseEntity.ok(produitService.findByCategorie(idCategorie));
    }

    /**
     * GET /api/produits/magasin/{idMagasin}
     */
    @GetMapping("/magasin/{idMagasin}")
    public ResponseEntity<List<ProduitDto>> findByMagasin(@PathVariable Integer idMagasin) {
        return ResponseEntity.ok(produitService.findByMagasin(idMagasin));
    }

    /**
     * GET /api/produits/alertes/rupture
     */
    @GetMapping("/alertes/rupture")
    public ResponseEntity<List<ProduitDto>> findEnRupture() {
        return ResponseEntity.ok(produitService.findProduitsEnRupture());
    }

    /**
     * GET /api/produits/alertes/seuil?seuil=10
     */
    @GetMapping("/alertes/seuil")
    public ResponseEntity<List<ProduitDto>> findSousSeuil(@RequestParam BigDecimal seuil) {
        return ResponseEntity.ok(produitService.findProduitsSousSeuil(seuil));
    }

    // ==================== MISE À JOUR ====================

    /**
     * PUT /api/produits/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProduitDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody ProduitDto dto) {
        return ResponseEntity.ok(produitService.update(id, dto));
    }

    // ==================== SUPPRESSION ====================

    /**
     * DELETE /api/produits/{id}
     * 204 No Content en cas de succès.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
