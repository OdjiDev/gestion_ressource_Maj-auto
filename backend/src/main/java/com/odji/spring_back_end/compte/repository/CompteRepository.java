package com.odji.spring_back_end.compte.repository;

import com.odji.spring_back_end.compte.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    @Query("SELECT c FROM Compte c WHERE c.deleted = false ORDER BY c.nom")
    List<Compte> findAllActive();

    @Query("SELECT c FROM Compte c WHERE c.deleted = false AND c.actif = true ORDER BY c.nom")
    List<Compte> findAllActiveAndEnabled();

    Optional<Compte> findByCode(String code);

    boolean existsByCode(String code);
}
