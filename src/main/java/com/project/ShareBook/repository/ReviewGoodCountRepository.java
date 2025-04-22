package com.project.ShareBook.repository;

import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.ReviewGoodCount;
import com.project.ShareBook.Entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewGoodCountRepository extends JpaRepository<ReviewGoodCount, Long> {
    Optional<ReviewGoodCount> findByUserAndReview(User user, BookReview review);

    @Query("SELECT SUM(r.goodCount) FROM ReviewGoodCount r WHERE r.review.id = :reviewId")
    Long getTotalGoodCountByReviewId(@Param("reviewId") Long reviewId);

    List<ReviewGoodCount> findByUserId(Long userId);
}
