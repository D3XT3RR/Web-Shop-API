package com.zarlok.webshop.restapi.service;

import com.zarlok.webshop.restapi.dao.ReviewRepository;
import com.zarlok.webshop.restapi.entity.Review;
import com.zarlok.webshop.restapi.exception.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private final ProductService productService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         ProductService productService){
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    public List<Review> findAll(int productId){
        return reviewRepository.findByProduct_IdOrderByIdAsc(productId);
    }

    public Review findById(int reviewId){
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (!optionalReview.isPresent()){
            throw new ReviewNotFoundException("Review not found with id - "+reviewId);
        }
        return optionalReview.get();
    }

    public Review findByProduct_IdAndId(int productId, int reviewId){
        Optional<Review> optionalReview = reviewRepository.findByProduct_IdAndId(productId, reviewId);
        if (!optionalReview.isPresent()){
            throw new ReviewNotFoundException("Review with id " + reviewId + " not found for product with id " + productId);
        }
        return optionalReview.get();
    }

    public Review save(Review review, int productId){
        review.setProduct(productService.findById(productId));
        return reviewRepository.save(review);
    }

    public Review update(Review review, int productId){
        if(this.appliesTo(review.getId(), productId)){
            review.setProduct(productService.findById(productId));
            return reviewRepository.save(review);
        }
        throw new ReviewNotFoundException("Review with id " + review.getId() + " not found for product with id " + productId);

    }

    public void deleteById(int reviewId, int productId){
        if(this.appliesTo(reviewId, productId)){
            reviewRepository.deleteById(reviewId);
        }
    }

    public boolean appliesTo(int reviewId, int productId){
        Optional<Review> optionalReview = reviewRepository.findByProduct_IdAndId(productId, reviewId);
        return optionalReview.isPresent();
    }
}
