package com.example.demoltitool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssignmentController {

    @GetMapping("/assignment")
    public String assignment(@RequestParam(name="name", required=false, defaultValue="Guest") String name,
                             Model model) {
        model.addAttribute("name", name);
        return "assignment";
    }
}
