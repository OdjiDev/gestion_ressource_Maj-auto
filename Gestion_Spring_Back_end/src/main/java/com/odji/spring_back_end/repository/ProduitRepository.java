package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {

    // ==================== Recherches simples ====================

    /**
     * ⚠️ BUG CORRIGÉ : "findArticleByCodeproduit" → Spring Data ne sait pas mapper
     * "Article" sur un champ de Produit. Il faut "findByCodeproduit".
     */
    Optional<Produit> findByCodeproduit(String codeproduit);

    boolean existsByCodeproduit(String codeproduit);

    // ==================== Recherches par relation ====================

    List<Produit> findAllByCategorieId(Integer idCategorie);

    List<Produit> findAllByMagasinId(Integer idMagasin);

    // ==================== Recherches paginées (recommandé pour les listes) ====================

    Page<Produit> findAllByCategorieId(Integer idCategorie, Pageable pageable);

    Page<Produit> findAllByMagasinId(Integer idMagasin, Pageable pageable);

    // ==================== Recherche par nom (insensible à la casse) ====================

    List<Produit> findByNomContainingIgnoreCase(String nom);

    Page<Produit> findByNomContainingIgnoreCase(String nom, Pageable pageable);

    // ==================== Requêtes avec fetch (anti N+1) ====================

    /**
     * Charge un produit avec sa catégorie et son magasin en UNE seule requête.
     * Évite le problème N+1 quand on sérialise le DTO.
     */
    @Query("""
        SELECT p FROM Produit p
        LEFT JOIN FETCH p.categorie
        LEFT JOIN FETCH p.magasin
        WHERE p.id = :id
    """)
    Optional<Produit> findByIdWithRelations(@Param("id") Integer id);

    /**
     * Liste paginée avec catégorie + magasin chargés.
     * Note : avec Page + JOIN FETCH, il faut compter séparément (d'où countQuery).
     */
    @Query(
            value = """
            SELECT p FROM Produit p
            LEFT JOIN FETCH p.categorie
            LEFT JOIN FETCH p.magasin
        """,
            countQuery = "SELECT COUNT(p) FROM Produit p"
    )
    Page<Produit> findAllWithRelations(Pageable pageable);

    // ==================== Alertes de stock ====================

    /**
     * Produits en rupture (quantité <= 0).
     */
    @Query("SELECT p FROM Produit p WHERE p.quantite <= 0")
    List<Produit> findProduitsEnRupture();

    /**
     * Produits sous un seuil donné.
     */
    @Query("SELECT p FROM Produit p WHERE p.quantite < :seuil")
    List<Produit> findProduitsSousSeuil(@Param("seuil") java.math.BigDecimal seuil);

    long countByCategorieId(Integer idCategorie);
}
