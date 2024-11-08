package com.example.httpserver.controller;

import com.example.httpserver.enums.Role;
import com.example.httpserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getUserByUsername(username));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{username}/role")
    public ResponseEntity setUserRole(@PathVariable String user, @RequestParam String writer, @RequestParam Role role) {
        try {
            return ResponseEntity.ok(userService.updateUserRole(writer, user, role));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{username}/username")
    public ResponseEntity setUserUsername(@PathVariable String user, @RequestParam String writer, @RequestParam String username) {
        try {
            return ResponseEntity.ok(userService.updateUserUsername(writer, user, username));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{username}/password")
    public ResponseEntity setUserPassword(@PathVariable String user, @RequestParam String writer, @RequestParam String password) {
        try {
            return ResponseEntity.ok(userService.updateUserPassword(writer, user, password));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUserById(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
