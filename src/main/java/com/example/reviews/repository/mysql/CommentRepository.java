package com.example.reviews.repository.mysql;

import com.example.reviews.entity.mysql.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentByReviewId(Integer id);
}
