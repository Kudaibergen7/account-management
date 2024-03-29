package com.example.accountmanagement.dto;

import com.example.accountmanagement.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Role role;

    public UserDTO(String firstName, String lastName, String username, String password) {
    }
}
