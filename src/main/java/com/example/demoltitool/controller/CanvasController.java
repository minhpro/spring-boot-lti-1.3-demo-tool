package com.example.demoltitool.controller;

import com.example.demoltitool.api.CanvasLMS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CanvasController {


    @Value("${canvas-api-base-url:http://localhost:3000/api/v1}")
    private String canvasApiBaseUrl;

    @GetMapping("/assignment")
    public String assignment(@RequestParam(name="name", required=false, defaultValue="Guest") String name,
                             Model model) {
        model.addAttribute("name", name);
        return "assignment";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("oauth2user", principal.getAttributes());
        return "profile";
    }


    @GetMapping("/courses")
    public String courses(@AuthenticationPrincipal OidcUser principal, Model model) {
        String token = principal.getIdToken().getTokenValue();
        CanvasLMS canvasLMS = new CanvasLMS(canvasApiBaseUrl, token);
        model.addAttribute("courses", canvasLMS.courses());
        return "courses";
    }

}
