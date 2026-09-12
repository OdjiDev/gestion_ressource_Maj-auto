package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.Option;
import org.springframework.context.annotation.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Integer> {
    boolean existsByNom(String nom);
    // all crud database methods
}
