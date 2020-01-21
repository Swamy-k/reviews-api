package com.example.reviews.controller;

import com.example.reviews.controller.dto.CommentDto;
import com.example.reviews.entity.mongo.CommentDocument;
import com.example.reviews.service.ReviewService;
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
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final ReviewService service;
    private final ModelMapper mapper;

    public CommentsController(ReviewService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Creates a comment for a review.
     * It Check for existence of review.
     * If review not found, return NOT_FOUND.
     * If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<List<CommentDto>> createCommentForReview(@PathVariable("reviewId") String  reviewId,
                                                                   @RequestBody @Valid CommentDto commentDto) {
        CommentDocument comment = mapper.map(commentDto, CommentDocument.class);
        List<CommentDocument> commentDocumentList = service.createComment(comment, reviewId);

        if (commentDocumentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type listType = new TypeToken<List<CommentDto>>() {}.getType();
        List<CommentDto> comments = mapper.map(commentDocumentList, listType);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * List comments for a review.
     *
     * It Check for existence of review.
     * If review not found, return NOT_FOUND.
     * If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<CommentDto>> listCommentsForReview(@PathVariable("reviewId") String reviewId) {
        List<CommentDocument> commentDocumentList = service.getAllCommentsByReviewId(reviewId);

        if (commentDocumentList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Type listType = new TypeToken<List<CommentDto>>() {}.getType();
        List<CommentDto> comments = mapper.map(commentDocumentList, listType);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
