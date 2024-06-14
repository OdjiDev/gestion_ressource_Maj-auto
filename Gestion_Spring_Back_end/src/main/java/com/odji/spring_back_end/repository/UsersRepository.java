package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {

    Users findByUserId(String userId);
}
