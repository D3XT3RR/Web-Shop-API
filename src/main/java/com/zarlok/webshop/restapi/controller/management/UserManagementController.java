package com.zarlok.webshop.restapi.controller.management;


import com.zarlok.webshop.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/users")
public class UserManagementController {

    final private UserService userService;

    @Autowired
    public UserManagementController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("users", userService.findAll());
        return "management/users/users.html";
    }
}
