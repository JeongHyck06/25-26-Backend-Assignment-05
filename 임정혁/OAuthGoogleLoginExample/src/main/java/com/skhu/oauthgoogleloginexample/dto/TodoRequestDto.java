package com.skhu.oauthgoogleloginexample.dto;

import com.skhu.oauthgoogleloginexample.domain.Priority;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoRequestDto {
    private String title;
    private String description;
    private Boolean completed;
    private Priority priority;
    private LocalDate dueDate;
}

