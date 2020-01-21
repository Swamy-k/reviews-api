package com.example.reviews.service;

import com.example.reviews.entity.mongo.CommentDocument;
import com.example.reviews.entity.mongo.ReviewDocument;

import java.util.List;

public interface ReviewService {
   ReviewDocument persist(ReviewDocument review);
   List<ReviewDocument> getAllByProductId(Integer productId);
   List<CommentDocument> getAllCommentsByReviewId(String reviewId);
   List<CommentDocument> createComment(CommentDocument comment, String reviewId);
   Double getAverageReviewScore(Integer productId);
}
