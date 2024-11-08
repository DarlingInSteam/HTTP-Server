package com.example.httpserver.service;

import com.example.httpserver.entity.UserEntity;
import com.example.httpserver.enums.Role;
import com.example.httpserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(String username, String password) {
        UserEntity user = new UserEntity();

        if (isUsernameTaken(username)) {
            throw new IllegalArgumentException("Username is already taken");
        }

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);

        userRepository.save(user);

        return username + "." + password;
    }

    public String authUser(String name, String password) {
        UserEntity user = userRepository.findByUsername(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return name + "." + password;
    }

    private boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
