package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.Personel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonelRepository extends JpaRepository<Personel, Integer> {

    // ==================== Recherche par matricule/code (si unique) ====================

    Optional<Personel> findByMatricule(String matricule);

    boolean existsByMatricule(String matricule);

    // ==================== Recherche par nom ====================

    List<Personel> findAllByNomContainingIgnoreCase(String nom);

    Page<Personel> findAllByNomContainingIgnoreCase(String nom, Pageable pageable);

    // ==================== Recherche par bureau ====================

 //   List<Personel> findAllByBureauId(Integer idBureau);

    //Page<Personel> findAllByBureauId(Integer idBureau, Pageable pageable);

    // ==================== Recherche par département ====================

  //  List<Personel> findAllByDepartementId(Integer idDepartement);

    // ==================== Statistiques ====================

    long countByBureauId(Integer idBureau);



    long countByRoleId(Integer id);

    Optional<Object> findByEmail(String email);
}
