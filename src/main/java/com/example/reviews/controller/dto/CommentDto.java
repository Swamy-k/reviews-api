package com.example.reviews.controller.dto;

import javax.validation.constraints.NotNull;

public class CommentDto {
    @NotNull
    private String authorName;
    @NotNull
    private String comment;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "authorName='" + authorName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
