package com.azeem.taskmanagementapi.controller;
import com.azeem.taskmanagementapi.dto.TaskRequest;
import com.azeem.taskmanagementapi.dto.TaskResponse;
import com.azeem.taskmanagementapi.entity.Task;
import com.azeem.taskmanagementapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @PostMapping("/api/tasks")
    public TaskResponse createTask(@RequestBody @Valid TaskRequest task){
        return taskService.createTask(task);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@RequestBody @Valid TaskRequest task, @PathVariable Long id){
        return taskService.updateTask(id,task);
    }


    @DeleteMapping("/{id}")

    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

}
