package com.example.todo.controller.task;

import com.example.todo.service.TaskEntity;

public record TaskDTO(
                long id,
                String summary,
                String description,
                String status
                ) {

        public static TaskDTO toDTO(TaskEntity taskEntity) {
                return new TaskDTO(
                                taskEntity.id(),
                                taskEntity.summary(),
                                taskEntity.description(),
                                taskEntity.status().name());
        }
}