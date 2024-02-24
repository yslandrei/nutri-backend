package com.example.nutribackend.domain.dto;

public record UserUpdatePasswordDTO(String oldPassword, String confirmedOldPassword, String newPassword) {
}
