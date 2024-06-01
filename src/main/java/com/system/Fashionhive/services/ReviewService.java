package com.system.Fashionhive.services;

import com.system.Fashionhive.entity.Review;
import com.system.Fashionhive.pojo.ReviewPojo;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewPojo reviewPojo);
    List<Review> getAllReviews(Integer productId);
    void deleteReview(Integer reviewId);
}
