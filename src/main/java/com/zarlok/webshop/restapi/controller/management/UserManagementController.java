package com.zarlok.webshop.restapi.controller.management;


import com.zarlok.webshop.restapi.entity.User;
import com.zarlok.webshop.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{username}")
    public String saveUserDetails(@ModelAttribute User user, @PathVariable String username){
        userService.updateDetails(user, username);
        return "redirect:/management/users";
    }

    @PostMapping("/{username}/password")
    public String changePassword(@PathVariable String username, @RequestParam("password") String newPassword){
        userService.updatePassword(username, newPassword);
        return "redirect:/management/users";
    }
}

