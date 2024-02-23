package com.example.nutribackend.repository;

import com.example.nutribackend.domain.UserAllergens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAllergensRepository extends JpaRepository<UserAllergens, String> {

}