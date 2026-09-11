package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.Facture;
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
public interface FactureRepository extends JpaRepository<Facture, Integer> {

    // ==================== Recherche par numero (unique) ====================

    Optional<Facture> findByNumero(String numero);

    boolean existsByNumero(String numero);

    // ==================== Recherche par code ====================

    List<Facture> findAllByCode(String code);

    // ==================== Recherche par fournisseur ====================

    List<Facture> findAllByFournisseurId(Integer idFournisseur);

    Page<Facture> findAllByFournisseurId(Integer idFournisseur, Pageable pageable);

    // ==================== Recherche par date ====================

    List<Facture> findAllByDatecommandeBetween(LocalDate debut, LocalDate fin);

    List<Facture> findAllByDatecommandeAfter(LocalDate date);

    List<Facture> findAllByDatecommandeBefore(LocalDate date);

    // ==================== Anti N+1 ====================

    @Query("""
        SELECT f FROM Facture f
        LEFT JOIN FETCH f.fournisseur
        WHERE f.id = :id
    """)
    Optional<Facture> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = """
            SELECT f FROM Facture f
            LEFT JOIN FETCH f.fournisseur
        """,
            countQuery = "SELECT COUNT(f) FROM Facture f"
    )
    Page<Facture> findAllWithRelations(Pageable pageable);

    // ==================== Statistiques ====================

    long countByFournisseurId(Integer idFournisseur);
}
