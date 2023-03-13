package com.zarlok.webshop.restapi.dao;

import com.zarlok.webshop.restapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByIdAndUser_Username(int id, String username);
    boolean existsByUser_Username(String username);
    Optional<Role> findByUser_Username(String username);
    Optional<Role> findByUser_UsernameAndId(String username, int id);
}