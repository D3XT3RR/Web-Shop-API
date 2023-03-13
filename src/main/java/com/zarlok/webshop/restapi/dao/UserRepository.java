package com.zarlok.webshop.restapi.dao;


import com.zarlok.webshop.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    void deleteByUsername(String username);
}