package com.example.accountmanagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        userRepository.saveUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername(), userDTO.getPassword());
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = userRepository.findAllUsers();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTOs.add(userDTO);
        }
        return userDTOs;
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
