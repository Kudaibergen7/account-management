package com.example.accountmanagement.user;

import com.example.accountmanagement.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task createTaskForUser(TaskDTO taskDTO, String assignedToUsername) {
        User assignedTo = userRepository.findByUsername(assignedToUsername);
        if (assignedTo == null) {
            throw new RuntimeException("User not found with username: " + assignedToUsername);
        }

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCreatedDateTime(LocalDateTime.now());
        task.setDeadline(taskDTO.getDeadline());
        task.setAssignedTo(assignedTo);
        task.setStatus(TaskStatus.TODO);

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasksForUser(String assignedToUsername) {
        return taskRepository.findByAssignedToUsername(assignedToUsername);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void changeTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setStatus(status);
            taskRepository.save(task);
        }
    }
}