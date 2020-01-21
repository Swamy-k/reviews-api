package com.example.reviews.repository.mongo;

import com.example.reviews.entity.mongo.CommentDocument;
import com.example.reviews.entity.mongo.ReviewDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewDocumentRepositoryTest {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ReviewDocumentRepository repository;

    @Test
    public void verify_injectedComponentsAreNotNull() {
        assertThat(mongoTemplate).isNotNull();
        assertThat(repository).isNotNull();
    }
    
    @Test
    public void verify_findAllByProductId() {
        // given
        CommentDocument comment = new CommentDocument();
        comment.setReviewId("1");
        comment.setAuthorName("Jhon");
        comment.setCreateDate(LocalDateTime.now());
        comment.setComment("I like this product");

        ReviewDocument review = new ReviewDocument();
        review.setProductId(10);
        review.setAuthorName("Jane");
        review.setTitle("Best Product");
        review.setCreateDate(LocalDateTime.now());
        review.setReview("This is the best Product Ever!");
        review.setComments(Collections.singletonList(comment));

        // when
        mongoTemplate.save(review, "review");

        // then
        List<ReviewDocument> reviews = repository.findAllByProductId(10);
        assertThat(reviews.size()).isEqualTo(1);
        assertThat(reviews.get(0).getAuthorName()).isEqualTo("Jane");
    }
}
