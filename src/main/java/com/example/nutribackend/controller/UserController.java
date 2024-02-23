package com.example.nutribackend.controller;

import com.example.nutribackend.domain.UserAllergens;
import com.example.nutribackend.domain.dto.AuthRequestDTO;
import com.example.nutribackend.domain.dto.UserAllergensDTO;
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
@RequestMapping("/api/v1/user/")
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserWithoutPassDTO> register(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/allergens")
    public ResponseEntity<UserAllergensDTO> getAllergens(@RequestParam String userId) {
        UserAllergensDTO userAllergensDTO = userService.getAllergensOfUser(userId);
        if (userAllergensDTO == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(userAllergensDTO);
    }

    @PatchMapping("/allergens")
    public ResponseEntity<Void> updateAllergens(@RequestParam String userId, @RequestBody UserAllergensDTO allergens) {
        return ResponseEntity.ok(userService.updateAllergensOfUser(userId, allergens));
    }
}
