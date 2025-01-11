package com.example.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    public List<TaskEntity> find() {
        var task1 = new TaskEntity(1L, "洗濯をする", "柔軟剤は使わないで", TaskStatus.TODO);
        var task2 = new TaskEntity(2L, "たまご買う", "サイズはM", TaskStatus.DOING);
        var taskList = List.of(task1, task2);
        return taskList;
    }
}
