package com.odji.spring_back_end.avarie.repository;

import com.odji.spring_back_end.avarie.entity.Reparer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReparerRepository  extends JpaRepository<Reparer, Integer> {
    List<Reparer> findAllByDateBetween(LocalDate debut, LocalDate fin);
}
