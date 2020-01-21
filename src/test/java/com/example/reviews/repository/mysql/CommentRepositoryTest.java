package com.example.reviews.repository.mysql;

import com.example.reviews.entity.mysql.Comment;
import com.example.reviews.entity.mysql.Product;
import com.example.reviews.entity.mysql.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void verify_injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(commentRepository).isNotNull();
    }

    @Test
    public void verify_findCommentsByReviewId() {
        // given
        Product product = new Product();
        product.setName("Samsung Mobile");
        product.setDescription("Samsung mobiles");
        product.setCompanyName("Samsung");
        testEntityManager.persist(product);

        Review review = new Review();
        review.setProduct(product);
        review.setAuthorName("Jhon");
        review.setTitle("What about its features?");
        review.setCreateDate(LocalDateTime.now());
        review.setReview("Awesome features");
        testEntityManager.persist(review);

        Comment comment = new Comment();
        comment.setReview(review);
        comment.setAuthorName("Jane");
        comment.setCreateDate(LocalDateTime.now());
        comment.setComment("Best Review");
        testEntityManager.persist(comment);
        testEntityManager.flush();

        // when
        List<Comment> found = commentRepository.findCommentByReviewId(1);

        // System.err.println(review);
        // System.err.println(found);

        // then
        assertEquals(1, found.size());
        assertEquals(comment.getAuthorName(), found.get(0).getAuthorName());
        assertEquals(comment.getReview(), found.get(0).getReview());
    }
}
