package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.FactureReparer;
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
public interface FactureReparerRepository extends JpaRepository<FactureReparer, Integer> {

    // ==================== Recherche par code ====================

    Optional<FactureReparer> findByCode(String code);

    boolean existsByCode(String code);

    // ==================== Recherche par société ====================

    List<FactureReparer> findAllBySocieteId(Integer idSociete);

    Page<FactureReparer> findAllBySocieteId(Integer idSociete, Pageable pageable);

    // ==================== Recherche par date ====================

    List<FactureReparer> findAllByDateBetween(LocalDate debut, LocalDate fin);

    List<FactureReparer> findAllByDateAfter(LocalDate date);

    List<FactureReparer> findAllByDateBefore(LocalDate date);

    // ==================== Anti N+1 ====================

    @Query("""
        SELECT f FROM FactureReparer f
        LEFT JOIN FETCH f.societe
        WHERE f.id = :id
    """)
    Optional<FactureReparer> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = """
            SELECT f FROM FactureReparer f
            LEFT JOIN FETCH f.societe
        """,
            countQuery = "SELECT COUNT(f) FROM FactureReparer f"
    )
    Page<FactureReparer> findAllWithRelations(Pageable pageable);

    // ==================== Statistiques ====================

    long countBySocieteId(Integer idSociete);
}
