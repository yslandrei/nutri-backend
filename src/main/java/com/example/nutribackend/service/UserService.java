package com.example.nutribackend.service;


import com.example.nutribackend.domain.User;
import com.example.nutribackend.domain.dto.UserAllergensDTO;
import com.example.nutribackend.domain.dto.UserDTO;
import com.example.nutribackend.domain.dto.UserWithoutPassDTO;

public interface UserService {
    boolean authorizeUser(String email, String password);

    UserWithoutPassDTO getUserByEmail(String email);

    UserWithoutPassDTO registerUser(UserDTO user);

    UserAllergensDTO getAllergensOfUser(String userId);

    Void updateAllergensOfUser(String userId, UserAllergensDTO allergens);
}