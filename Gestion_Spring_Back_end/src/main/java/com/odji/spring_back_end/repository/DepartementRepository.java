package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {

    // ==================== Par code (unique) ====================
    Optional<Departement> findByCode(String code);
    boolean existsByCode(String code);

    // ==================== Par nom ====================
    Optional<Departement> findByNom(String nom);
    List<Departement> findAllByNomContainingIgnoreCase(String nom);
    Page<Departement> findAllByNomContainingIgnoreCase(String nom, Pageable pageable);
}
