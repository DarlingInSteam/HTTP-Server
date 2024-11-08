package com.example.httpserver.controller;

import com.example.httpserver.enums.Role;
import com.example.httpserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired private UserService userService;

    @GetMapping()
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
    public ResponseEntity setUserRole(@PathVariable String username, @RequestParam String writer, @RequestParam Role role) {
        try {
            return ResponseEntity.ok(userService.updateUserRole(writer, username, role));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{username}/username")
    public ResponseEntity setUserUsername(@PathVariable String username, @RequestParam String writer, @RequestParam String newUsername) {
        try {
            return ResponseEntity.ok(userService.updateUserUsername(writer, username, newUsername));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{username}/password")
    public ResponseEntity setUserPassword(@PathVariable String username, @RequestParam String writer, @RequestParam String password) {
        try {
            return ResponseEntity.ok(userService.updateUserPassword(writer, username, password));
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
