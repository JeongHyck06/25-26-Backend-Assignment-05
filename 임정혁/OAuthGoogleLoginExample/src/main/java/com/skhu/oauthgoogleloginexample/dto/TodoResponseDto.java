package com.skhu.oauthgoogleloginexample.dto;

import com.skhu.oauthgoogleloginexample.domain.Priority;
import com.skhu.oauthgoogleloginexample.domain.Todo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class TodoResponseDto {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Priority priority;
    private LocalDate dueDate;
    private Long userId;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TodoResponseDto from(Todo todo) {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.getCompleted())
                .priority(todo.getPriority())
                .dueDate(todo.getDueDate())
                .userId(todo.getUser().getId())
                .userName(todo.getUser().getUserName())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
}

