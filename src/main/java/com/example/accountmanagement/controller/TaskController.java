package com.example.accountmanagement.user;

import com.example.accountmanagement.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTaskForUser(@RequestBody TaskDTO taskDTO, @RequestParam String assignedToUsername) {
        Task createdTask = taskService.createTaskForUser(taskDTO, assignedToUsername);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasksForUser(@RequestParam String assignedToUsername) {
        List<Task> tasks = taskService.getAllTasksForUser(assignedToUsername);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        Task existingTask = taskService.getTaskById(taskId);
        if (existingTask != null) {
            task.setId(taskId);
            taskService.updateTask(task);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Void> changeTaskStatus(@PathVariable Long taskId, @RequestParam TaskStatus status) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            taskService.changeTaskStatus(taskId, status);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

