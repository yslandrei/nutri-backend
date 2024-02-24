package com.example.nutribackend.service;


import com.example.nutribackend.domain.User;
import com.example.nutribackend.domain.dto.*;

public interface UserService {
    boolean authorizeUser(String email, String password);

    UserWithoutPassDTO getUserByEmail(String email);

    UserWithoutPassDTO registerUser(UserDTO user);

    UserAllergensDTO getAllergensOfUser(String userId);

    Void updateAllergensOfUser(String userId, UserAllergensDTO allergens);

    void updateUserProfile(String userId, UserProfileDTO user);

    UserProfileDTO findUser(String userId);

    void updateUserPassword(String userId, UserUpdatePasswordDTO data);
}