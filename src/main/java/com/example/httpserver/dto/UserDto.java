package com.example.httpserver.dto;

import com.example.httpserver.entity.UserEntity;

public class UserDto {
    private Long id;
    private String username;
    private String role;

    public UserDto() {
    }

    public UserDto(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public static UserDto toDto(UserEntity user) {
        return new UserDto(user.getId(), user.getUsername(), user.getRole().name());
    }
}
