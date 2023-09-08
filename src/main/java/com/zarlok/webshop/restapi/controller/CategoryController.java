package com.zarlok.webshop.restapi.controller;


import com.zarlok.webshop.restapi.entity.Category;
import com.zarlok.webshop.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(path = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "categories/categories.html";
    }

    @GetMapping(path = "/{id}/edit")
    public String editCategory(Model model, @PathVariable int id){
        model.addAttribute("category",categoryService.findById(id));
        model.addAttribute("imageExist", categoryService.isImageAvailable(id));
        return "management/categories/edit.html";
    }

    @GetMapping(path = "/add")
    public String addCategory(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories/add.html";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute("category") Category category){
        int id = categoryService.save(category).getId();
        return "redirect:/categories/"+id+"/edit";
    }

    @PostMapping(path = "/{id}/uploadPhoto")
    public String addCategory(@PathVariable int id, @RequestParam("image") MultipartFile file) throws IOException {
        categoryService.saveImage(id, file);
        return "redirect:/categories/"+id+"/edit";
    }

    @GetMapping(path = "/{id}/delete")
    public String deleteCategory(@PathVariable int id, @RequestHeader("Referer") String referer){
        categoryService.delete(id);
        return "redirect:"+referer;
    }
}
