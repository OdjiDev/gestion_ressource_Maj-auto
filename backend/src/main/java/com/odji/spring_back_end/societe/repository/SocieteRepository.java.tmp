package com.odji.spring_back_end.societe.repository;

import com.odji.spring_back_end.societe.entity.Societe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocieteRepository  extends JpaRepository<Societe, Integer> {
    boolean existsByNumerofiscal(String numerofiscal);

    List<Societe> findAllByNomContainingIgnoreCase(String nom);
    // all crud database methods
}
