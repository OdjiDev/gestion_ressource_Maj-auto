package com.odji.spring_back_end.avarie.repository;

import com.odji.spring_back_end.avarie.entity.Avarie;
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
public interface AvarieRepository extends JpaRepository<Avarie, Integer> {

    // ==================== Recherche par produit ====================

    /**
     * Toutes les avaries d'un produit donné.
     * Spring Data génère : SELECT * FROM avarie WHERE idproduit = ?
     */
    List<Avarie> findAllByProduitId(Integer idProduit);

    Page<Avarie> findAllByProduitId(Integer idProduit, Pageable pageable);

    // ==================== Recherche par date ====================

    List<Avarie> findAllByDateBetween(LocalDate debut, LocalDate fin);

    List<Avarie> findAllByDateAfter(LocalDate date);

    // ==================== Recherche par motif ====================

    List<Avarie> findAllByMotifContainingIgnoreCase(String motif);

    // ==================== Anti N+1 ====================

    /**
     * Charge une avarie avec son produit associé en 1 seule requête.
     */
    @Query("""
        SELECT a FROM Avarie a
        LEFT JOIN FETCH a.produit
        WHERE a.id = :id
    """)
    Optional<Avarie> findByIdWithRelations(@Param("id") Integer id);

    /**
     * Liste paginée avec le produit chargé.
     */
    @Query(
            value = """
            SELECT a FROM Avarie a
            LEFT JOIN FETCH a.produit
        """,
            countQuery = "SELECT COUNT(a) FROM Avarie a"
    )
    Page<Avarie> findAllWithRelations(Pageable pageable);

    // ==================== Statistiques ====================

    /**
     * Nombre d'avaries pour un produit donné.
     */
    long countByProduitId(Integer idProduit);
}
