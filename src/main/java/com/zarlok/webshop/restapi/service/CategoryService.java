package com.zarlok.webshop.restapi.service;

import com.zarlok.webshop.restapi.dao.CategoryRepository;
import com.zarlok.webshop.restapi.entity.Category;
import com.zarlok.webshop.restapi.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            throw new CategoryNotFoundException("Not found category with id - "+id);
        }
        return optionalCategory.get();
    }

    public void saveImage(int id, MultipartFile file) throws IOException {
        Path path = Paths.get("target/classes/static/images/categories/"+id+".jpg");
        if (!file.isEmpty()) {
            Files.write(path, file.getBytes());
        }
    }

    public boolean isImageAvailable(int id){
        Path path = Paths.get("target/classes/static/images/categories/"+id+".jpg");
        return path.toFile().exists();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}
