package com.skhu.oauthgoogleloginexample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String userName;

    private String password;

    @Column(nullable = false)
    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public User(Long id, String email, String userName, String password, String profileUrl, Role role, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.profileUrl = profileUrl;
        this.role = role;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }
}
