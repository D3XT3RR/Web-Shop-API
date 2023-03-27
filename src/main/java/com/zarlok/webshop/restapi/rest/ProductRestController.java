package com.zarlok.webshop.restapi.rest;

import com.zarlok.webshop.restapi.entity.Product;
import com.zarlok.webshop.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public Product findById(@PathVariable int productId){
        return productService.findById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody Product product){
        product.setId(0);
        return productService.save(product);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@PathVariable int productId, @RequestBody Product product){
        product.setId(productId);
        return productService.save(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int productId){
        productService.deleteById(productId);
    }
}
