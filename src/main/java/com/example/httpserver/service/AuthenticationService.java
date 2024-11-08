package com.example.httpserver.service;
import com.example.httpserver.dto.AuthenticationResponse;
import com.example.httpserver.entity.RefreshToken;
import com.example.httpserver.entity.UserEntity;
import com.example.httpserver.enums.Role;
import com.example.httpserver.repository.PasswordResetTokenRepository;
import com.example.httpserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public AuthenticationResponse register(String username, String password) {
        UserEntity user = new UserEntity();

        if (isUsernameTaken(username)) {
            throw new IllegalArgumentException("Username is already taken");
        }

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return new AuthenticationResponse(jwtToken, refreshToken.getToken());
    }

    private boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public AuthenticationResponse authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );

        var user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return new AuthenticationResponse(jwtToken, refreshToken.getToken());
    }
}
