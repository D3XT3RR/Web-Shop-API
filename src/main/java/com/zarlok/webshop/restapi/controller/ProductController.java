package com.zarlok.webshop.restapi.controller;

import com.zarlok.webshop.restapi.entity.Product;
import com.zarlok.webshop.restapi.service.CategoryService;
import com.zarlok.webshop.restapi.service.ProductService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(path="/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listProducts(Model model){
        model.addAttribute("products",productService.findAll());
        return "products/products.html";
    }

    @GetMapping (path = "/{id}")
    String showProduct(Model model, @PathVariable int id){
        model.addAttribute("product", productService.findById(id));
        return "products/product.html";
    }

    @GetMapping("/{id}/edit")
    String editProduct(Model model, @PathVariable int id){
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("imageExist", productService.isImageAvailable(id));
        //File file = Paths.get("/images/"+id+".jpg").toFile();
        //model.addAttribute("image", file);
        return "products/editProduct.html";
    }

    @PostMapping
    String saveProduct(@ModelAttribute("product") Product product){
        System.out.println(product);
        int id = productService.save(product).getId();
        return "redirect:/products/"+id+"/edit";
    }
    @PostMapping("{id}/uploadPhoto")
    String uploadPhoto(@PathVariable int id, @RequestParam("image") MultipartFile file) throws IOException {
        productService.saveImage(id, file);
        return "redirect:/products/{id}/edit";
    }

    @GetMapping("/category/{category}")
    String showProductsInCategory(@PathVariable String category, Model model){
        model.addAttribute("products",productService.findByCategory(category));
        return "products/products.html";
    }

    @GetMapping(path = "/add")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("imageExist", false);
        return "products/add.html";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id, @RequestHeader("Referer") String referer){
        productService.delete(id);
        return "redirect:"+referer;
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String keyword, Model model){
        if (keyword == null){
            model.addAttribute("products", productService.findAll());
        }
        else{
            model.addAttribute("products", productService.findByName(keyword));
        }

        return "products/products.html";
    }



}
