package com.project.ShareBook.repository;

import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.ReviewGoodCount;
import com.project.ShareBook.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewGoodCountRepository extends JpaRepository<ReviewGoodCount, Long> {
    Optional<ReviewGoodCount> findByUserAndReview(User user, BookReview review);
}
