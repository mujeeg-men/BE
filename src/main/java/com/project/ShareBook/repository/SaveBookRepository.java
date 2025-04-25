package com.project.ShareBook.repository;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.SaveBook;
import com.project.ShareBook.Entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveBookRepository extends JpaRepository<SaveBook, Long> {
    boolean existsByUserAndBook(User user, Book book);

    List<SaveBook> findByUserId(Long userId);

    Optional<SaveBook> findByIdAndUserId(Long saveBookId, Long userId );

    void deleteSaveBookById(Long saveBookId);


}
