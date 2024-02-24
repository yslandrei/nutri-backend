package com.example.nutribackend.service;

import com.example.nutribackend.domain.User;
import com.example.nutribackend.domain.UserAllergens;
import com.example.nutribackend.domain.dto.*;
import com.example.nutribackend.domain.exception.ResourceAlreadyExistsException;
import com.example.nutribackend.domain.exception.ResourceNotFoundException;
import com.example.nutribackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authorizeUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UserWithoutPassDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        return new UserWithoutPassDTO(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserWithoutPassDTO registerUser(UserDTO user) {
        userRepository.findByEmail(user.email()).ifPresent((foundUser) -> {
            throw new ResourceAlreadyExistsException("User with email " + foundUser.getEmail() + " already exists");
        });

        User newUser = new User(null, user.name(), user.email(), LocalDateTime.now(), bCryptPasswordEncoder.encode(user.password()), null);
        newUser = userRepository.save(newUser);
        return new UserWithoutPassDTO(newUser.getId(), newUser.getName(), newUser.getEmail());
    }

    @Override
    public UserAllergensDTO getAllergensOfUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        return UserAllergensDTO.fromUserAllergens(user.getAllergens());
    }

    @Override
    public Void updateAllergensOfUser(String userId, UserAllergensDTO allergens) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        UserAllergens userAllergens = allergens.toUserAllergens();
        user.setAllergens(userAllergens);
        userRepository.save(user);
        return null;
    }

    @Override
    public void updateUserProfile(String userId, UserProfileDTO data) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        user.setEmail(data.email());
        user.setName(data.name());
        userRepository.save(user);
    }

    @Override
    public UserProfileDTO findUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        return new UserProfileDTO(user.getName(), user.getEmail());
    }

    @Override
    public void updateUserPassword(String userId, UserUpdatePasswordDTO data) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        if (!Objects.equals(data.oldPassword(), data.confirmedOldPassword())) {
            throw new ResourceNotFoundException("Passwords do not match!");
        }
        if (!bCryptPasswordEncoder.matches(data.oldPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Passwords do not match!");
        }
        user.setPassword(bCryptPasswordEncoder.encode(data.newPassword()));
    }
}