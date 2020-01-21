package com.example.reviews.repository.mongo;

import com.example.reviews.entity.mongo.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDocumentRepository extends MongoRepository<ReviewDocument, String> {
    List<ReviewDocument> findAllByProductId(Integer productId);
}
