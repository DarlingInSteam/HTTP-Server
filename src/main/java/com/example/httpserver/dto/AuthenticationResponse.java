package com.example.httpserver.dto;

import com.example.httpserver.entity.RefreshToken;

public class AuthenticationResponse {
    String accessToken;
    String refreshToken;

    public AuthenticationResponse() {}

    public AuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
