package com.zarlok.webshop.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController{

    @GetMapping("/login")
    public String login(Model model){
        return "login.html";
    }

//    @PostMapping("/login")
//    public String handleLogin(@RequestParam("username") String username, @RequestParam("password") String password){
//
//    }
}
