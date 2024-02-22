package com.example.nutribackend.service;


import com.example.nutribackend.domain.User;
import com.example.nutribackend.domain.dto.UserDTO;
import com.example.nutribackend.domain.dto.UserWithoutPassDTO;

import java.util.List;

public interface UserService {
    boolean authorizeUser(String email, String password);

    UserWithoutPassDTO getUserByEmail(String email);

    UserWithoutPassDTO registerUser(UserDTO user);
}