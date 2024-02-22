package com.example.nutribackend.controller;

import com.example.nutribackend.domain.dto.AuthRequestDTO;
import com.example.nutribackend.domain.dto.UserDTO;
import com.example.nutribackend.domain.dto.UserWithoutPassDTO;
import com.example.nutribackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nutribackend.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authorize")
    public ResponseEntity<UserWithoutPassDTO> authorize(@RequestBody AuthRequestDTO request) {
        if (userService.authorizeUser(request.email(), request.password())) {
            return ResponseEntity.ok(userService.getUserByEmail(request.email()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserWithoutPassDTO> register(@RequestBody UserDTO user) {
        UserWithoutPassDTO userWithoutPass = userService.registerUser(user);
        if (userWithoutPass != null) {
            return ResponseEntity.ok(userWithoutPass);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}
