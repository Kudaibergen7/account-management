package com.example.accountmanagement.user;

import com.example.accountmanagement.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedToUsername(String assignedToUsername);
}

