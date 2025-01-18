package com.example.todo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        var taskDTO = taskService.findById(taskId)
        .map(TaskDTO::toDTO)
                .orElseThrow(TaskNotFoundException::new);
        model.addAttribute("task", taskDTO);

        return "tasks/detail";
    }

    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute TaskForm form) {

        // @ModelAttribute TaskForm formを使用することで、以下のコードは不要になります。
        // thymeleafのformタグ内でtaskFormを使用することで、formの初期値を設定することができます。

        // if (form == null) {
        // form = new TaskForm(null, null, null);
        // }
        // model.addAttribute("taskForm", form);

        return "tasks/form";
    }

    @PostMapping
    public String create(@Validated TaskForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // return "tasks/form"; この方法は、入力値が保持されないため、推奨されません。（uxの観点で非推奨です）
            return showCreationForm(form);
        }
        taskService.create(form.toEntity());
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/editForm")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        var form = taskService.findById(id)
                .map(TaskForm::fromEntity)
                .orElseThrow(TaskNotFoundException::new);

        // これは冗長なコードです。
        // var form = new TaskForm(taskEntity.summary(), taskEntity.description(),
        // taskEntity.status().name());

        // Controller側でEntity変数を保持するのは好ましくないため、以下のコードは不要です。
        // var form = TaskForm.fromEntity(taskEntity);

        model.addAttribute("taskForm", form);
        return "tasks/form";

    }
}
