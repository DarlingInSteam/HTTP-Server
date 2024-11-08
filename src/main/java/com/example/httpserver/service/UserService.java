package com.example.httpserver.service;

import com.example.httpserver.dto.UserDto;
import com.example.httpserver.enums.Role;
import com.example.httpserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userEntity -> new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getRole().name()))
                .orElseThrow();
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getRole().name()))
                .orElseThrow();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteUserByUsername(String username) {
        userRepository.findByUsername(username)
                .ifPresent(userRepository::delete);
    }

    public void updateUserRole(String usernameWriter, String usernameEditing, Role role) {

    }

    public void updateUserPassword(String usernameWriter, String usernameEditing, String password) {

    }

    public void updateUserUsername(String usernameWriter, String usernameEditing, String newUsername) {

    }
}
