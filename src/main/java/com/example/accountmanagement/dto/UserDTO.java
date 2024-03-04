package com.example.accountmanagement.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String username;

    private String password;
}
