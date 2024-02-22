package com.example.nutribackend.service;

import com.example.nutribackend.domain.User;
import com.example.nutribackend.domain.dto.UserDTO;
import com.example.nutribackend.domain.dto.UserWithoutPassDTO;
import com.example.nutribackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        User user = userRepository.findByEmail(email);
        return user != null && bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UserWithoutPassDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return new UserWithoutPassDTO(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserWithoutPassDTO registerUser(UserDTO user) {
        if (userRepository.findByEmail(user.email()) != null) {
            return null;
        }
        User newUser = new User(null, user.name(), user.email(), bCryptPasswordEncoder.encode(user.password()));
        newUser = userRepository.save(newUser);
        return new UserWithoutPassDTO(newUser.getId(), newUser.getName(), newUser.getEmail());
    }

}