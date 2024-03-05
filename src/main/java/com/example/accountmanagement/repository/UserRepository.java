package com.example.accountmanagement.repository;

import com.example.accountmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET password = :newPassword WHERE username = :username AND password = :oldPassword", nativeQuery = true)
    int updatePassword(String username, String oldPassword, String newPassword);
}

