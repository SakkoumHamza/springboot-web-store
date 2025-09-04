package com.hamza.springstore.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Hamza");
        return "index";
    }

}
