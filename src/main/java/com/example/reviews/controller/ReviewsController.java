package com.example.reviews.controller;

import com.example.reviews.controller.dto.ReviewDto;
import com.example.reviews.entity.mongo.ReviewDocument;
import com.example.reviews.service.ReviewService;
import com.example.reviews.service.ReviewServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    private final ReviewService service;
    private final ModelMapper mapper;

    public ReviewsController(ReviewService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Creates a review for a product.
     * Checks for existence of product.
     * If product not found, return NOT_FOUND.
     * If found, saving the review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<ReviewDto> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                            @RequestBody @Valid ReviewDto reviewDto) {
        ReviewDocument review = mapper.map(reviewDto, ReviewDocument.class);
        ReviewDocument savedDocument = service.persist(review);
        ReviewDto savedReview = mapper.map(savedDocument, ReviewDto.class);
        return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDto>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        List<ReviewDocument> reviewDocumentList = service.getAllByProductId(productId);

        if (reviewDocumentList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type listType = new TypeToken<List<ReviewDto>>() {}.getType();
        List<ReviewDto> reviews = mapper.map(reviewDocumentList, listType);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
