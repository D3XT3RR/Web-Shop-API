package com.zarlok.webshop.restapi.rest;

import com.zarlok.webshop.restapi.entity.Review;
import com.zarlok.webshop.restapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewRestController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> findAll(@PathVariable int productId){
        return reviewService.findAll(productId);
    }

    @GetMapping("/{reviewId}")
    public Review findReviewOfProductById(@PathVariable int productId, @PathVariable int reviewId){
        return reviewService.findByProduct_IdAndId(productId, reviewId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review save(@RequestBody Review review, @PathVariable int productId){
        review.setId(0);
        return reviewService.save(review, productId);
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Review update(@PathVariable int productId, @PathVariable int reviewId, @RequestBody Review review){
        review.setId(reviewId);
        return reviewService.update(review, productId);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewOfProduct(@PathVariable int productId, @PathVariable int reviewId){
        reviewService.deleteById(reviewId, productId);
    }


}
