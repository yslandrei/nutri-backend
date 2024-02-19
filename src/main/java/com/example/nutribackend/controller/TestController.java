package com.example.nutribackend.controller;

import com.example.nutribackend.model.User;
import com.example.nutribackend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {
    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public List<String> test() {
        return testRepository.findAll().stream().map(User::getName).toList();
    }
}
