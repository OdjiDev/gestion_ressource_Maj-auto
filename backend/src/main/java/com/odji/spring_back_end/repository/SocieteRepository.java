package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.Societe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocieteRepository  extends JpaRepository<Societe, Integer> {
    boolean existsByNumerofiscal(String numerofiscal);

    List<Societe> findAllByNomContainingIgnoreCase(String nom);
    // all crud database methods
}
