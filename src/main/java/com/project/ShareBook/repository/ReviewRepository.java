package com.project.ShareBook.repository;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.BookReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<BookReview, Long> {
    BookReview findByBook(Book bookId);

}
