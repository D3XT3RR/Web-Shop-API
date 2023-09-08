package com.zarlok.webshop.restapi.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/management")
public class ManagementController{

    @GetMapping
    public String showManagement(){
        return "management/index.html";
    }
}
