package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.todo.service.TaskService;


@Controller
// @RequiredArgsConstructor
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

        // このコードは@ControllerでEntityを扱うので非推奨コードです
        // model.addAttribute("demo", taskService.find());

        return "tasks/list";
    }
}


