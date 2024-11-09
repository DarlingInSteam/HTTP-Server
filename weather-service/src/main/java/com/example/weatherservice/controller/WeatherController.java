package com.example.weatherservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller()
public class WeatherController {
    private final String API_KEY = "02e1cfacf3f1425e92564911240911";
    private final String BASE_URL = "http://api.weatherapi.com/v1/current.json";

    @GetMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        String url = String.format("%s?key=%s&q=%s", BASE_URL, API_KEY, city);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }
}
