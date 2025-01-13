package com.example.todo.controller.task;

import com.example.todo.service.TaskEntity;
import com.example.todo.service.TaskStatus;

public record TaskForm(
    String summary,
    String description,
    String status
) {
    public TaskEntity toEntity() {
        return new TaskEntity(null, summary(), description(), TaskStatus.valueOf(status));
    }
}
