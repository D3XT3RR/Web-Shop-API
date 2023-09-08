package com.zarlok.webshop.restapi.dao;

import com.zarlok.webshop.restapi.entity.Category;
import com.zarlok.webshop.restapi.entity.Product;
import com.zarlok.webshop.restapi.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.name like concat('%', ?1, '%')")
    List<Product> findByNameContains(String name);
    List<Product> findByNameLikeIgnoreCaseOrderByIdAsc(String name);
    List<Product> findByCategory_Name(String category_name);
    List<Product> findByCategory_Id(int id);
    @Transactional
    @Modifying
    @Query("update Product p set p.name = ?1, p.price = ?2, p.available = ?3, p.quantity = ?4, p.category = ?5, p.unit =?6 where p.id = ?1")
    Product update(int id, String name, double price, boolean availability, int quantity, Category category, Unit unit);
}