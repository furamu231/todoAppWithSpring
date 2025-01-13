package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.service.TaskService;

@Controller
@RequestMapping("/tasks")
// @RequiredArgsConstructor Lombokの読み込みに失敗するため、使用することができません。
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String list(Model model) {
        var taskList = taskService.find().stream()
                .map(TaskDTO::toDTO)
                .toList();
        model.addAttribute("taskList", taskList);
        return "tasks/list";
    }
    
    @GetMapping("/{id}")
    public String showDetail(@PathVariable("id") long taskId, Model model) {
        // taskId -> TaskEntity
        var taskEntity = taskService.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found. taskId: " + taskId));
                model.addAttribute("task", TaskDTO.toDTO(taskEntity));    

        return "tasks/detail";
    }

    @GetMapping("/creationForm")
    public String showCreationForm() {
        return "tasks/form";
    }

    @PostMapping
    public String create(TaskForm form, Model model) {
        taskService.create(form.toEntity());
        return "redirect:/tasks";
    }
}


