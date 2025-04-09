package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.dto.BookResponseDto;
import com.project.ShareBook.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchAndSaveBooks(@RequestParam String bookName) {
        List<Book> books = bookService.searchAndSaveBooks(bookName);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    // 책 상세 페이지 진입 api
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookDetail(@PathVariable Long bookId){
        Book bookById = bookService.findBookById(bookId);
        return ResponseEntity.ok(bookById);
    }
    //카테고리별 책 리스트 조회 api

}
