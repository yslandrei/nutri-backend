package com.example.nutribackend.controller;

import com.example.nutribackend.domain.dto.*;
import com.example.nutribackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/settings/profile/{userId}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable String userId, @RequestBody UserProfileDTO user) {
        userService.updateUserProfile(userId, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<UserProfileDTO> findUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PostMapping("/settings/profile/{userId}/updatePassword")
    public ResponseEntity<Void> updatePassword(@PathVariable String userId, @RequestBody UserUpdatePasswordDTO data) {
        userService.updateUserPassword(userId, data);
        return ResponseEntity.ok().build();
    }
}
