package com.example.gym_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController {

    @GetMapping("/usersView")
    public String usersPage() {
        return "user";
    }
}
