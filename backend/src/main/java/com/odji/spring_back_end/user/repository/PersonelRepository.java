package com.odji.spring_back_end.user.repository;

import com.odji.spring_back_end.user.entity.Personel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonelRepository extends JpaRepository<Personel, Integer> {


    long countByRoleId(Integer id);
}
