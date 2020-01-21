package com.example.reviews.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("review")
public class ReviewDocument {

    @Id
    private String id;
    private Integer productId;
    private String authorName;
    private String title;
    private LocalDateTime createDate;
    private String review;
    private List<CommentDocument> comments = new ArrayList<>();
    private Integer score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<CommentDocument> getComments() {
        return comments;
    }

    public void setComments(List<CommentDocument> comments) {
        this.comments = comments;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ReviewDocument{" +
                "id='" + id + '\'' +
                ", productId=" + productId +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", createDate=" + createDate +
                ", review='" + review + '\'' +
                ", comments=" + comments +
                ", score=" + score +
                '}';
    }
}
