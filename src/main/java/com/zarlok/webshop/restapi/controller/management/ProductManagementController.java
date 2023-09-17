package com.zarlok.webshop.restapi.controller.management;

import com.zarlok.webshop.restapi.entity.Product;
import com.zarlok.webshop.restapi.service.CategoryService;
import com.zarlok.webshop.restapi.service.ProductService;
import com.zarlok.webshop.restapi.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/management/products")
public class ProductManagementController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UnitService unitService;

    @Autowired
    public ProductManagementController(ProductService productService, CategoryService categoryService, UnitService unitService){
        this.productService = productService;
        this.categoryService = categoryService;
        this.unitService = unitService;
    }

    @GetMapping
    public String listProducts(Model model){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("units", unitService.findAll());
        return "management/products/products.html";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product){

        productService.save(product);

        return "redirect:/management/products";
    }

    @PostMapping("{id}/uploadPhoto")
    String uploadPhoto(@PathVariable int id, @RequestParam("image") MultipartFile file) throws IOException {
        productService.saveImage(id, file);
        return "redirect:/management/products";
    }
}
