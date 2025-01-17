package com.example.todo.controller.task;

import com.example.todo.service.TaskEntity;
import com.example.todo.service.TaskStatus;

import jakarta.validation.constraints.NotBlank;

public record TaskForm (

    @NotBlank
    String summary,

    String description,
    
    @NotBlank
    String status
) {
    public TaskEntity toEntity() {
        return new TaskEntity(null, summary(), description(), TaskStatus.valueOf(status));
    }
}
