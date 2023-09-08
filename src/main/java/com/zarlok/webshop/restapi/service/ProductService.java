package com.zarlok.webshop.restapi.service;

import com.zarlok.webshop.restapi.dao.ProductRepository;
import com.zarlok.webshop.restapi.entity.Product;
import com.zarlok.webshop.restapi.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()){
            throw new ProductNotFoundException("Not found product with id - " + id);
        }
        return optionalProduct.get();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product update(Product product){
        System.out.println(product.toString());
        //return productRepository.update(product.getId(), product.getName(), product.getPrice(), product.isAvailable(), product.getQuantity(), product.getCategory());
        return  productRepository.save(product);
    }

    public void saveImage(int id, MultipartFile file) throws IOException {
        Path path = Paths.get("target/classes/static/images/products/"+id+".jpg");
        if (!file.isEmpty()) {
            Files.write(path, file.getBytes());
        }

    }

    public void deleteById(int id){
        productRepository.deleteById(id);
    }

    public List<Product> findByCategory(String categoryName){
        System.out.println(productRepository.findByCategory_Name(categoryName));
        return productRepository.findByCategory_Name(categoryName);
    }


    public boolean isImageAvailable(int id) {
        Path path = Paths.get("target/classes/static/images/products/"+id+".jpg");
        return path.toFile().exists();
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByName(String keyword) {
        return productRepository.findByNameContains(keyword);
    }
}
