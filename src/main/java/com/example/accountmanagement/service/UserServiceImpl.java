package com.example.accountmanagement.service;

import com.example.accountmanagement.entity.User;
import com.example.accountmanagement.dto.UserDTO;
import com.example.accountmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return userDTO;
    }


    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword()))
                .collect(Collectors.toList());
    }


    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public int changePassword(String username, String oldPassword, String newPassword) {
        return userRepository.updatePassword(username, oldPassword, newPassword);
    }

    public int deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return 1;
        }
        return 0;
    }
}
