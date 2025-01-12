package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.service.TaskEntity;
import com.example.todo.service.TaskService;
import com.example.todo.service.TaskStatus;


@Controller
// @RequiredArgsConstructor Lombokの読み込みに失敗するため、使用することができません。
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String list(Model model) {
        var taskList = taskService.find().stream()
                .map(TaskDTO::toDTO)
                .toList();
        model.addAttribute("taskList", taskList);
        return "tasks/list";
    }
    
    @GetMapping("/tasks/{id}")
    public String showDetail(@PathVariable("id") long taskId, Model model) {
        // taskId -> TaskEntity
        var taskEntity = taskService.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found. taskId: " + taskId));
                model.addAttribute("task", TaskDTO.toDTO(taskEntity));    

        return "tasks/detail";
    }

    @GetMapping("/tasks/creationForm")
    public String showCreationForm() {
        return "tasks/form";
    }

    @PostMapping("/tasks")
    public String create(TaskForm form, Model model) {
        var newEntity = new TaskEntity(null, form.summary(), form.description(), TaskStatus.valueOf(form.status()));
        taskService.create(newEntity);
        // ２重サブミットを防ぐため、リダイレクトを行います。
        return "redirect:/tasks";
    }
}


