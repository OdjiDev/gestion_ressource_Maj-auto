package com.odji.spring_back_end.affectation.repository;

import com.odji.spring_back_end.affectation.entity.Affectation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Integer> {

        // ==================== Recherches par relation ====================

        List<Affectation> findAllByProduitId(Integer idProduit);

        List<Affectation> findAllByPersonelId(Integer idPersonel);

        // ==================== Recherches paginées ====================

        Page<Affectation> findAllByProduitId(Integer idProduit, Pageable pageable);

        Page<Affectation> findAllByPersonelId(Integer idPersonel, Pageable pageable);

        // ==================== Recherches par date ====================

        List<Affectation> findAllByDateBetween(LocalDate debut, LocalDate fin);

        List<Affectation> findAllByDateAfter(LocalDate date);

        List<Affectation> findAllByDateBefore(LocalDate date);

        // ==================== Anti N+1 (fetch relations) ====================

        @Query("""
        SELECT a FROM Affectation a
        LEFT JOIN FETCH a.produit
        LEFT JOIN FETCH a.personel
        WHERE a.id = :id
    """)
        java.util.Optional<Affectation> findByIdWithRelations(@Param("id") Integer id);

        @Query(
                value = """
            SELECT a FROM Affectation a
            LEFT JOIN FETCH a.produit
            LEFT JOIN FETCH a.personel
        """,
                countQuery = "SELECT COUNT(a) FROM Affectation a"
        )
        Page<Affectation> findAllWithRelations(Pageable pageable);
}
