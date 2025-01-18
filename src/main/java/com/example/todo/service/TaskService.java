package com.example.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.repository.task.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> find() {
        var taskList = taskRepository.select();
        return taskList;
    }
    
    // TaskEntityが存在しない可能性を考慮して、Optional<TaskEntity>を返すように設計しています。
    public Optional<TaskEntity> findById(long taskId) {
        return taskRepository.selectById(taskId);
        
    }
    
    @Transactional
    public void create(TaskEntity taskEntity) {
        taskRepository.insert(taskEntity);
    }

    @Transactional
    public void update(TaskEntity taskEntity) {
        System.out.println("taskEntityのID: " + taskEntity.id());
        taskRepository.update(taskEntity);
    }
}
