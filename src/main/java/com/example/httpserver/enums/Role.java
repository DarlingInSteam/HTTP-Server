package com.example.httpserver.enums;

public enum Role {
    USER,
    ADMIN;

    public static Role getRoleFromString(String role) {
        return switch (role) {
            case "USER" -> USER;
            case "ADMIN" -> ADMIN;
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }
}