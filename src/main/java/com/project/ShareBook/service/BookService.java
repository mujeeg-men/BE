package com.project.ShareBook.service;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.config.BookApiClient;
import com.project.ShareBook.dto.BookRequestDto;
import com.project.ShareBook.dto.BookResponseDto;
import com.project.ShareBook.repository.BookRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookApiClient bookApiClient;

    @Transactional
    public BookResponseDto getAndSaveBooks(String isbn) {
        Book book = bookRepository.findByBookIsbn(isbn)
            .orElseGet(() -> {
                Book newBook = bookApiClient.getBookByIsbn(isbn);
                return bookRepository.save(newBook);
            });

        return BookResponseDto.from(book);
    }

    public Book findBookById(Long bookId){
        return bookRepository.findById(bookId)
            .orElseThrow(()->new IllegalArgumentException("존재하지 않는 책입니다"));
    }
}
