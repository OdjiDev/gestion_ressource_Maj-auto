package com.odji.spring_back_end.societe.repository;

import com.odji.spring_back_end.societe.entity.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository  extends JpaRepository<Contrat, Integer> {
    boolean existsByCode(String code);
    // all crud database methods
}

