package com.odji.spring_back_end.signalement.repository;

import com.odji.spring_back_end.signalement.entity.Signaler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignalerRepository extends JpaRepository<Signaler, Integer> {

    // ==================== Par produit ====================
    List<Signaler> findAllByProduitId(Integer idProduit);
    long countByProduitId(Integer idProduit);

    // ==================== Par personel ====================
    List<Signaler> findAllByPersonelId(Integer idPersonel);
    long countByPersonelId(Integer idPersonel);

    // ==================== Par état ====================
    List<Signaler> findAllByEtat(String etat);
    Page<Signaler> findAllByEtat(String etat, Pageable pageable);
    long countByEtat(String etat);

    // ==================== Anti N+1 ====================
    @Query("""
        SELECT s FROM Signaler s
        LEFT JOIN FETCH s.produit
        LEFT JOIN FETCH s.personel
        WHERE s.id = :id
    """)
    Optional<Signaler> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = """
            SELECT s FROM Signaler s
            LEFT JOIN FETCH s.produit
            LEFT JOIN FETCH s.personel
        """,
            countQuery = "SELECT COUNT(s) FROM Signaler s"
    )
    Page<Signaler> findAllWithRelations(Pageable pageable);
}
