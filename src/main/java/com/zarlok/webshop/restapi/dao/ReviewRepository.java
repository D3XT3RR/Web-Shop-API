package com.zarlok.webshop.restapi.dao;

import com.zarlok.webshop.restapi.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByProduct_IdAndId(int productId, int reviewId);
    List<Review> findByProduct_IdOrderByIdAsc(int id);
}