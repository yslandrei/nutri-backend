package com.example.nutribackend.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String email;

    private LocalDateTime emailVerified;

    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAllergens allergens;

    public User(String id, String name, String email, LocalDateTime emailVerified, String password, UserAllergens allergens) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailVerified = emailVerified;
        this.password = password;
        this.allergens = allergens;
    }

    public LocalDateTime getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(LocalDateTime emailVerified) {
        this.emailVerified = emailVerified;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAllergens getAllergens() {
        return allergens;
    }

    public void setAllergens(UserAllergens allergens) {
        this.allergens = allergens;
        this.allergens.setUser(this);
    }
}