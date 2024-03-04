package com.example.accountmanagement.user;


import com.example.accountmanagement.Task;

import java.util.List;

    public interface TaskService {
        Task createTaskForUser(TaskDTO taskDTO, String assignedToUsername);

        List<Task> getAllTasksForUser(String assignedToUsername);

        Task getTaskById(Long taskId);

        void updateTask(Task task);

        void deleteTask(Long taskId);

        void changeTaskStatus(Long taskId, TaskStatus status);
    }
