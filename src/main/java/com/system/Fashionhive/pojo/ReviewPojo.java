package com.system.Fashionhive.pojo;

import com.system.Fashionhive.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPojo {
    private Integer id;
    private Integer product;
    private Integer user;
    private Integer rate;
    private String review;
    private String date;

    public ReviewPojo(Review reviews) {
        this.id = reviews.getId();
        this.product = reviews.getProduct().getId();
        this.user = reviews.getUser().getId();
        this.rate = reviews.getRate();
        this.review = reviews.getReview();
        this.date = String.valueOf(reviews.getDate());
    }
}
