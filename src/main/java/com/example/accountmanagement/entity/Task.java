package com.example.accountmanagement;

import com.example.accountmanagement.user.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdDateTime;

    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.example.accountmanagement.user.User assignedTo;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

}
