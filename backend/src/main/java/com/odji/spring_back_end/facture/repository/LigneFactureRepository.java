package com.odji.spring_back_end.facture.repository;

import com.odji.spring_back_end.facture.entity.LigneFacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LigneFactureRepository extends JpaRepository<LigneFacture, Integer> {

    // ==================== Recherche par facture ====================

    List<LigneFacture> findAllByFactureId(Integer idFacture);

    Page<LigneFacture> findAllByFactureId(Integer idFacture, Pageable pageable);

    // ==================== Recherche par produit ====================

    List<LigneFacture> findAllByProduitId(Integer idProduit);

    // ==================== Recherche par date ====================

    List<LigneFacture> findAllByDateBetween(LocalDate debut, LocalDate fin);

    // ==================== Anti N+1 ====================

    @Query("""
        SELECT l FROM LigneFacture l
        LEFT JOIN FETCH l.produit
        LEFT JOIN FETCH l.facture
        WHERE l.id = :id
    """)
    Optional<LigneFacture> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = """
            SELECT l FROM LigneFacture l
            LEFT JOIN FETCH l.produit
        """,
            countQuery = "SELECT COUNT(l) FROM LigneFacture l"
    )
    Page<LigneFacture> findAllWithRelations(Pageable pageable);

    // ==================== Statistiques ====================

    long countByFactureId(Integer idFacture);

    // ==================== Suppression en masse ====================

    /**
     * Supprime toutes les lignes d'une facture.
     * Utile dans le service quand on supprime une facture.
     */
    void deleteAllByFactureId(Integer idFacture);
}
