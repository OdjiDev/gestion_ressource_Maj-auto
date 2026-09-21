package com.odji.spring_back_end.fournisseur.repository;

import com.odji.spring_back_end.fournisseur.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FournisseurRepository  extends JpaRepository<Fournisseur, Integer> {
    List<Fournisseur> findAllByNomContainingIgnoreCase(String nom);
    // all crud database methods
}

