package com.example.httpserver.controller;

import com.example.httpserver.dto.AuthenticationResponse;
import com.example.httpserver.dto.RefreshTokenRequest;
import com.example.httpserver.service.AuthenticationService;
import com.example.httpserver.service.JwtService;
import com.example.httpserver.service.RefreshTokenService;
import com.example.httpserver.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired private AuthenticationService authService;

    @Autowired private RefreshTokenService refreshTokenService;

    @Autowired private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestParam String username, @RequestParam String password) {
        try {
            return ResponseEntity.ok(authService.register(username, password));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity authUser(@RequestParam String username, @RequestParam String password) {
        try {
            return ResponseEntity.ok(authService.authenticate(username, password));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getToken();
        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user);
                    return new AuthenticationResponse(accessToken, refreshToken);
                }).orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));
    }
}
