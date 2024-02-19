package com.example.nutribackend.repository;

import com.example.nutribackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<User, Long> {

}