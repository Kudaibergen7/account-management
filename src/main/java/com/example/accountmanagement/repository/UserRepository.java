package com.example.accountmanagement.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (first_name, last_name, username, password) VALUES (:firstName, :lastName, :username, :password)", nativeQuery = true)
    void saveUser(String firstName, String lastName, String username, String password);

        @Transactional
        @Modifying
        @Query(value = "UPDATE users SET password = :newPassword WHERE username = :username AND password = :oldPassword", nativeQuery = true)
        int updatePassword(String username, String oldPassword, String newPassword);

        @Query(value = "SELECT * FROM users", nativeQuery = true)
        List<User> findAllUsers();
    }
