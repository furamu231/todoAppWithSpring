package com.example.todo.service;

public record TaskEntity(
        long id,
        String summary,
        String description,
        TaskStatus status
        ) {

}
