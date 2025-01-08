package com.example.gym_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController {

    @GetMapping("/users")
    public String usersPage() {
        for(int i = 0; i < 10; i++){
            System.out.println("XD");
        }
        return "users2";


    }
}
