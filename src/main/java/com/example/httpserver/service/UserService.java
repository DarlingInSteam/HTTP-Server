package com.example.httpserver.service;

import com.example.httpserver.dto.UserDto;
import com.example.httpserver.enums.Role;
import com.example.httpserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getRole().name()))
                .orElseThrow();
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getRole().name()))
                .orElseThrow();
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getRole().name()))
                .toList();
    }

    public boolean deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);

        return true;
    }

    public void deleteUserByUsername(String username) {
        userRepository.findByUsername(username)
                .ifPresent(userRepository::delete);
    }

    public UserDto updateUserRole(String usernameWriter, String usernameEditing, Role role) {
        var userWriter = userRepository.findByUsername(usernameWriter).orElseThrow();
        if (userWriter.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Only admin can change user role");
        }

        return userRepository.findByUsername(usernameEditing)
                .map(user -> {
                    user.setRole(role);
                    userRepository.save(user);
                    return UserDto.toDto(user);
                }).orElseThrow();
    }

    public UserDto updateUserPassword(String usernameWriter, String usernameEditing, String password) {
        var userWriter = userRepository.findByUsername(usernameWriter).orElseThrow();
        if (userWriter.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Only admin can change user password");
        }

        return userRepository.findByUsername(usernameEditing)
                .map(user -> {
                    user.setPassword(password);
                    userRepository.save(user);
                    return UserDto.toDto(user);
                }).orElseThrow();
    }

    public UserDto updateUserUsername(String usernameWriter, String usernameEditing, String newUsername) {
        var userWriter = userRepository.findByUsername(usernameWriter).orElseThrow();
        if (userWriter.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Only admin can change user username");

        }

        return userRepository.findByUsername(usernameEditing)
                .map(user -> {
                    user.setUsername(newUsername);
                    userRepository.save(user);
                    return UserDto.toDto(user);
                }).orElseThrow();
    }
}