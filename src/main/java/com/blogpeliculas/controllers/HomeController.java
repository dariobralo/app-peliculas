package com.blogpeliculas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @PostMapping("/login")
    public String login(){
        return "/home";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
