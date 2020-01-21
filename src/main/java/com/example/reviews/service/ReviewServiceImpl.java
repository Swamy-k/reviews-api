package com.example.reviews.service;

import com.example.reviews.entity.mongo.CommentDocument;
import com.example.reviews.entity.mongo.ReviewDocument;
import com.example.reviews.repository.mongo.ReviewDocumentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDocumentRepository repository;

    public ReviewServiceImpl(ReviewDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public ReviewDocument persist(ReviewDocument review) {
        review.setCreateDate(LocalDateTime.now());
        return repository.save(review);
    }

    @Override
    public List<ReviewDocument> getAllByProductId(Integer productId) {
        return repository.findAllByProductId(productId);
    }

    @Override
    public List<CommentDocument> getAllCommentsByReviewId(String reviewId) {
        Optional<ReviewDocument> optionalReview = repository.findById(reviewId);
        return optionalReview.map(reviewDocument -> new ArrayList<>(reviewDocument.getComments())).orElse(null);
    }

    @Override
    public List<CommentDocument> createComment(CommentDocument comment, String reviewId) {
        Optional<ReviewDocument> optionalReview = repository.findById(reviewId);

        if (!optionalReview.isPresent()) {
            return null;
        }

        ReviewDocument reviewDocument = optionalReview.get();
        List<CommentDocument> comments = reviewDocument.getComments();
        comment.setCreateDate(LocalDateTime.now());
        comments.add(comment);
        reviewDocument = persist(reviewDocument);

        return reviewDocument.getComments();

    }

    @Override
    public Double getAverageReviewScore(Integer productId) {
        List<ReviewDocument> reviews = getAllByProductId(productId);
        OptionalDouble optionalScore = reviews.stream()
                .map(ReviewDocument::getScore)
                .filter(Objects::nonNull)
                .mapToDouble(Double::valueOf)
                .average();

        return optionalScore.orElse(0.0);
    }
}
