package com.project.ShareBook.service;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.config.BookApiClient;
import com.project.ShareBook.dto.BookRequestDto;
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
    public List<Book> searchAndSaveBooks(String request) {

        List<Book> books = bookRepository.findByBookNameContaining(request);
        if (books.isEmpty()) {
//            log.info(" [DB 조회] '{}' 검색 결과: {}개", request, books.size());
//            return ResponseEntity.status(HttpStatus.OK).body(books);
            books = bookApiClient.searchBooks(request);
            bookRepository.saveAll(books);
            log.info("[DB 저장 완료] '{}' 검색 결과 {}개 추가", request, books.size());
        }
        // 저장된 데이터 응답으로 반환
        return books;
    }
    public Book findBookById(Long bookId){
        return bookRepository.findById(bookId)
            .orElseThrow(()->new IllegalArgumentException("존재하지 않는 책입니다"));
    }
}
