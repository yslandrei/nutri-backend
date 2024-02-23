package com.example.nutribackend.service;

import com.example.nutribackend.domain.User;
import com.example.nutribackend.domain.UserAllergens;
import com.example.nutribackend.domain.dto.UserAllergensDTO;
import com.example.nutribackend.domain.dto.UserDTO;
import com.example.nutribackend.domain.dto.UserWithoutPassDTO;
import com.example.nutribackend.domain.exception.ResourceAlreadyExistsException;
import com.example.nutribackend.domain.exception.ResourceNotFoundException;
import com.example.nutribackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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

        User newUser = new User(null, user.name(), user.email(), bCryptPasswordEncoder.encode(user.password()));
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
}