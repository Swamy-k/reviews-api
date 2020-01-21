package com.example.reviews.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReviewDto {
    private Integer productId;
    @NotNull
    private String authorName;
    @NotNull
    private String title;
    @NotNull
    private String review;
    @Min(0)
    @Max(5)
    private Integer score;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "productId=" + productId +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", review='" + review + '\'' +
                ", score=" + score +
                '}';
    }
}
