package com.skhu.oauthgoogleloginexample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean completed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Todo(Long id, String title, String description, Boolean completed, Priority priority, LocalDate dueDate, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed != null ? completed : false;
        this.priority = priority != null ? priority : Priority.MEDIUM;
        this.dueDate = dueDate;
        this.user = user;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt;
    }

    public void update(String title, String description, Boolean completed, Priority priority, LocalDate dueDate) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
        if (description != null) {
            this.description = description;
        }
        if (completed != null) {
            this.completed = completed;
        }
        if (priority != null) {
            this.priority = priority;
        }
        if (dueDate != null) {
            this.dueDate = dueDate;
        }
        this.updatedAt = LocalDateTime.now();
    }
}

