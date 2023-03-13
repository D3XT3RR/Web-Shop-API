package com.zarlok.webshop.restapi.service;

import com.zarlok.webshop.restapi.dao.ProductRepository;
import com.zarlok.webshop.restapi.entity.Product;
import com.zarlok.webshop.restapi.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteById(int id){
        productRepository.deleteById(id);
    }


}
