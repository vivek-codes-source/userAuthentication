package com.example.UserService.Dtos;


import com.example.UserService.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class UserDto {
    private String email;

    private Set<Role> roles = new HashSet<>();
}