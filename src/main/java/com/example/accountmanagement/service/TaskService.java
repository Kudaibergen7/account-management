package com.example.accountmanagement.service;


import com.example.accountmanagement.entity.Task;
import com.example.accountmanagement.dto.TaskDTO;
import com.example.accountmanagement.enums.TaskStatus;

import java.util.List;

public interface TaskService {
    Task createTaskForUser(TaskDTO taskDTO, String assignedToUsername);

    List<Task> getAllTasksForUser(String assignedToUsername);

    Task getTaskById(Long taskId);

    void updateTask(Task task);

    void deleteTask(Long taskId);

    void changeTaskStatus(Long taskId, TaskStatus status);
}

