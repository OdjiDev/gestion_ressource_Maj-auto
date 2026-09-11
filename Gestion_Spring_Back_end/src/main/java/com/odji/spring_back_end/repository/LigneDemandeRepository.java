package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.LigneDemande;
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
public interface LigneDemandeRepository extends JpaRepository<LigneDemande, Integer> {

    // ==================== Recherche par demande ====================

    List<LigneDemande> findAllByDemandeId(Integer idDemande);

    Page<LigneDemande> findAllByDemandeId(Integer idDemande, Pageable pageable);

    // ==================== Recherche par produit ====================

    List<LigneDemande> findAllByProduitId(Integer idProduit);

    // ==================== Recherche par date ====================

    List<LigneDemande> findAllByDateBetween(LocalDate debut, LocalDate fin);

    // ==================== Anti N+1 ====================

    @Query("""
        SELECT l FROM LigneDemande l
        LEFT JOIN FETCH l.produit
        LEFT JOIN FETCH l.demande
        WHERE l.id = :id
    """)
    Optional<LigneDemande> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = """
            SELECT l FROM LigneDemande l
            LEFT JOIN FETCH l.produit
        """,
            countQuery = "SELECT COUNT(l) FROM LigneDemande l"
    )
    Page<LigneDemande> findAllWithRelations(Pageable pageable);

    // ==================== Statistiques ====================

    long countByDemandeId(Integer idDemande);
}
