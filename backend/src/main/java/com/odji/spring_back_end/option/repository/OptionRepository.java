package com.odji.spring_back_end.option.repository;

import com.odji.spring_back_end.option.entity.Option;
import org.springframework.context.annotation.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Integer> {
    boolean existsByNom(String nom);
    // all crud database methods
}
