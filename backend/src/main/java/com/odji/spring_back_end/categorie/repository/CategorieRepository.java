package com.odji.spring_back_end.categorie.repository;

import com.odji.spring_back_end.categorie.entity.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

    // ==================== Recherche par code (unique) ====================

    Optional<Categorie> findByCode(String code);

    boolean existsByCode(String code);

    // ==================== Recherche par nom ====================

    Optional<Categorie> findByNom(String nom);

    List<Categorie> findAllByNomContainingIgnoreCase(String nom);

    Page<Categorie> findAllByNomContainingIgnoreCase(String nom, Pageable pageable);

    boolean existsByNom(String nom);
}
