package com.zarlok.webshop.restapi.controller.management;

import com.zarlok.webshop.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/categories")
public class CategoryManagementController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryManagementController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "management/categories/categories.html";
    }
}
