package com.lab02.locadora.repositorys;

import java.util.List;

import com.lab02.locadora.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByEmail(String email);
}
