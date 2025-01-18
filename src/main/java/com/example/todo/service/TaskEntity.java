package com.example.todo.service;

public record TaskEntity(
                Long id,
                String summary,
                String description,
                TaskStatus status) {

        // public TaskEntity withId(Long id) {
        //         return new TaskEntity(id, this.summary, this.description, this.status);
        // }

}
