package com.odji.spring_back_end.affectation.repository;

import com.odji.spring_back_end.affectation.entity.Bureau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BureauRepository extends JpaRepository<Bureau, Integer> {

    // ==================== Recherche par département ====================

    /**
     * Tous les bureaux d'un département.
     */
    List<Bureau> findAllByDepartementId(Integer idDepartement);

    Page<Bureau> findAllByDepartementId(Integer idDepartement, Pageable pageable);

    // ==================== Recherche par nom ====================

    List<Bureau> findAllByNomContainingIgnoreCase(String nom);

    Optional<Bureau> findByNom(String nom);

    boolean existsByNom(String nom);

    // ==================== Anti N+1 ====================

    @Query("""
        SELECT b FROM Bureau b
        LEFT JOIN FETCH b.departement
        WHERE b.id = :id
    """)
    Optional<Bureau> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = """
            SELECT b FROM Bureau b
            LEFT JOIN FETCH b.departement
        """,
            countQuery = "SELECT COUNT(b) FROM Bureau b"
    )
    Page<Bureau> findAllWithRelations(Pageable pageable);

    // ==================== Statistiques ====================

    long countByDepartementId(Integer idDepartement);
}
