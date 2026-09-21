package com.odji.spring_back_end.avarie.repository;

import com.odji.spring_back_end.avarie.entity.LigneReparation;
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
public interface LigneReparationRepository extends JpaRepository<LigneReparation, Integer> {

    // ==================== Recherche par réparer ====================

    List<LigneReparation> findAllByReparerId(Integer idReparer);

    Page<LigneReparation> findAllByReparerId(Integer idReparer, Pageable pageable);

    // ==================== Recherche par produit ====================

    List<LigneReparation> findAllByProduitId(Integer idProduit);

    // ==================== Recherche par date ====================

    List<LigneReparation> findAllByDateBetween(LocalDate debut, LocalDate fin);

    // ==================== Anti N+1 ====================

    @Query("""
        SELECT l FROM LigneReparation l
        LEFT JOIN FETCH l.produit
        LEFT JOIN FETCH l.reparer
        WHERE l.id = :id
    """)
    Optional<LigneReparation> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = """
            SELECT l FROM LigneReparation l
            LEFT JOIN FETCH l.produit
        """,
            countQuery = "SELECT COUNT(l) FROM LigneReparation l"
    )
    Page<LigneReparation> findAllWithRelations(Pageable pageable);

    // ==================== Statistiques ====================

    long countByReparerId(Integer idReparer);
}
