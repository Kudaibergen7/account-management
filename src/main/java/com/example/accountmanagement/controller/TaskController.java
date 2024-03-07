package com.example.accountmanagement.controller;

import com.example.accountmanagement.entity.Task;
import com.example.accountmanagement.dto.TaskDTO;
import com.example.accountmanagement.mapper.TaskMapper;
import com.example.accountmanagement.service.TaskService;
import com.example.accountmanagement.enums.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper mapper;

    public TaskController(TaskService taskService, TaskMapper mapper) {
        this.taskService = taskService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> createTaskForUser(@RequestBody TaskDTO taskDTO,
                                                     @RequestParam String assignedToUsername) {
        Task createdTask = taskService.createTaskForUser(taskDTO, assignedToUsername);
        TaskDTO createdTaskDTO = mapper.convertToDto(createdTask);
        return new ResponseEntity<>(createdTaskDTO, HttpStatus.CREATED);
    }




    @GetMapping("/allTasksForUser")
    public ResponseEntity<List<TaskDTO>> getAllTasksForUser(@RequestParam String assignedToUsername) {
        List<Task> tasks = taskService.getAllTasksForUser(assignedToUsername);
        List<TaskDTO> taskDTOs = tasks.stream().map(mapper::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
    }

    @GetMapping("/getTask/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            return new ResponseEntity<>(mapper.convertToDto(task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable Long taskId,
                                           @RequestBody TaskDTO taskDTO) {
        Task existingTask = taskService.getTaskById(taskId);
        if (existingTask != null) {
            Task updatedTask = mapper.convertToEntity(taskDTO);
            updatedTask.setId(taskId);
            taskService.updateTask(updatedTask);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/changeStatus/{taskId}")
    public ResponseEntity<Void> changeTaskStatus(@PathVariable Long taskId,
                                                 @RequestParam TaskStatus status) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            taskService.changeTaskStatus(taskId, status);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


