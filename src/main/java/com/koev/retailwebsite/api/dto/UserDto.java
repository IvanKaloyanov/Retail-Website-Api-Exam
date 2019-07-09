package com.koev.retailwebsite.api.dto;

import lombok.Data;

import java.util.List;

/**
 * User information representation
 */
@Data
public class UserDto {

    private String username;
    private String password;
    private List<String> roles;
}
