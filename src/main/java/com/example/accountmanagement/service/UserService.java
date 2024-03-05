package com.example.accountmanagement.service;

import com.example.accountmanagement.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    boolean login(String username, String password);

    int changePassword(String username, String oldPassword, String newPassword);

    int deleteUserByUsername(String username);
}

