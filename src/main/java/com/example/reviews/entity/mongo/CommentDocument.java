package com.example.reviews.entity.mongo;

import java.time.LocalDateTime;

public class CommentDocument {

    private String reviewId;
    private String authorName;
    private LocalDateTime createDate;
    private String comment;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentDocument{" +
                "reviewId='" + reviewId + '\'' +
                ", authorName='" + authorName + '\'' +
                ", createDate=" + createDate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
