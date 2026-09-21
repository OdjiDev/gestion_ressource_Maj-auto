package com.odji.spring_back_end.facture.repository;

import com.odji.spring_back_end.facture.entity.LigneFactureReparer;
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
public interface LigneFactureReparerRepository
        extends JpaRepository<LigneFactureReparer, Integer> {

    // ==================== Recherche par facture réparer ====================

    List<LigneFactureReparer> findAllByFactureReparerId(Integer idFactureReparer);

    Page<LigneFactureReparer> findAllByFactureReparerId(Integer idFactureReparer,
                                                        Pageable pageable);

    // ==================== Recherche par produit ====================

    List<LigneFactureReparer> findAllByProduitId(Integer idProduit);

    // ==================== Recherche par réparer ====================

    List<LigneFactureReparer> findAllByReparerId(Integer idReparer);

    // ==================== Recherche par date ====================

    List<LigneFactureReparer> findAllByDateBetween(LocalDate debut, LocalDate fin);

    // ==================== Anti N+1 ====================

    @Query("""
        SELECT l FROM LigneFactureReparer l
        LEFT JOIN FETCH l.produit
        LEFT JOIN FETCH l.reparer
        LEFT JOIN FETCH l.factureReparer
        WHERE l.id = :id
    """)
    Optional<LigneFactureReparer> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = """
            SELECT l FROM LigneFactureReparer l
            LEFT JOIN FETCH l.produit
        """,
            countQuery = "SELECT COUNT(l) FROM LigneFactureReparer l"
    )
    Page<LigneFactureReparer> findAllWithRelations(Pageable pageable);

    // ==================== Statistiques ====================

    long countByFactureReparerId(Integer idFactureReparer);

    // ==================== Suppression en masse ====================

    void deleteAllByFactureReparerId(Integer idFactureReparer);
}
