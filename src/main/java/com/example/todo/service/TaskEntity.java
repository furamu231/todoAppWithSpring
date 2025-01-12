package com.example.todo.service;

public record TaskEntity(
        Long id,
        String summary,
        String description,
        TaskStatus status
        ) {

}
