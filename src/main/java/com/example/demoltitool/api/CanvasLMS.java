package com.example.demoltitool.api;

import java.util.Map;

public class CanvasLMS extends ApiBinding {
    private String baseUrl;

    public CanvasLMS(String baseUrl, String accessToken) {
        super(accessToken);
        this.baseUrl = baseUrl;
    }

    public Map<String, Object> profile() {
        return restTemplate.getForObject(baseUrl + "/profile", Map.class);
    }

    public Map<String, Object> courses() {
        return restTemplate.getForObject(baseUrl + "/courses", Map.class);
    }
}
