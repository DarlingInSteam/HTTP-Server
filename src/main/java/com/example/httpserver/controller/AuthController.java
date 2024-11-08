package com.example.httpserver.controller;

import com.example.httpserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestParam String username, @RequestParam String password) {
        try {
            return ResponseEntity.ok(authService.registerUser(username, password));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity authUser(@RequestParam String username, @RequestParam String password) {
        try {
            return ResponseEntity.ok(authService.authUser(username, password));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
