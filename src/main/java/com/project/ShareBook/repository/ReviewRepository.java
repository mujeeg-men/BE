package com.project.ShareBook.repository;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<BookReview, Long> {
    BookReview findByBook(Book bookId);
    BookReview findByUser(User userId);
    Optional<BookReview> findByIdAndUserId(Long bookId, Long userId);

    // 삭제되지 않은 리뷰 전체 조회
    List<BookReview> findByIsDeletedFalse();

    // 유저별 삭제되지 않은 리뷰 조회
    List<BookReview> findByUserIdAndIsDeletedFalse(Long userId);

    // 책별 삭제되지 않은 리뷰 조회
    List<BookReview> findByBookIdAndIsDeletedFalse(Long bookId);


    List<BookReview> findByBookAndIsDeletedFalse(Book book);
}
