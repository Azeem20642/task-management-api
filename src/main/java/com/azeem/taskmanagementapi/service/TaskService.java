package com.azeem.taskmanagementapi.service;


import com.azeem.taskmanagementapi.dto.TaskRequest;
import com.azeem.taskmanagementapi.dto.TaskResponse;
import com.azeem.taskmanagementapi.entity.Task;
import com.azeem.taskmanagementapi.exception.TaskNotFoundException;
import com.azeem.taskmanagementapi.respository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    public TaskResponse createTask(TaskRequest task){
        Task t = new Task();
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setCompleted(task.isCompleted());
        Task savedTask = taskRepository.save(t);

        return mapToResponse(savedTask);
    }

    public List<TaskResponse> getAllTasks(){
        return taskRepository.findAll().stream().map(task->mapToResponse(task)).toList();
    }
    public TaskResponse getTaskById(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task Not Found "+ id));

        return mapToResponse(task);

    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public TaskResponse updateTask (Long id, TaskRequest task){
        Task existingTask = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task Not Found "+ id));
        if(existingTask == null) return null;
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());
        return mapToResponse(existingTask);
        
    }

    private TaskResponse mapToResponse(Task task) {

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.isCompleted());

        return response;
    }
}
