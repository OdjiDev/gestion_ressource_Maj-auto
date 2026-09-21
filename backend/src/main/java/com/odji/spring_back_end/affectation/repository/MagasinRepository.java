package com.odji.spring_back_end.affectation.repository;

import com.odji.spring_back_end.affectation.entity.Magasin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Integer> {

    // ==================== Recherche par nom ====================

    Optional<Magasin> findByNom(String nom);

    boolean existsByNom(String nom);

    List<Magasin> findAllByNomContainingIgnoreCase(String nom);

    Page<Magasin> findAllByNomContainingIgnoreCase(String nom, Pageable pageable);
}
