package com.example.httpserver.dto;

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
}
