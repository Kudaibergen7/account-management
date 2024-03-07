package com.example.accountmanagement.auth;

import com.example.accountmanagement.dto.UserDTO;
import com.example.accountmanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management")
public class ManagementController {

    private final UserService userService;

    public ManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password) {
        boolean loggedIn = userService.login(username, password);
        if (loggedIn) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PutMapping("/users/{username}/password")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> changePassword(@PathVariable String username,
                                            @RequestParam String oldPassword,
                                            @RequestParam String newPassword) {
        int updated = userService.changePassword(username, oldPassword, newPassword);
        if (updated == 1) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or old password is incorrect");
        }
    }

    @DeleteMapping("/users/{username}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        int deleted = userService.deleteUserByUsername(username);
        if (deleted == 1) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}

