package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.common.ApiResponse;
import com.project.ShareBook.common.SuccessType;
import com.project.ShareBook.config.BookApiClient;
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
    private final BookApiClient bookApiClient;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Book>>> searchBooks(@RequestParam String bookName) {
        List<Book> books = bookApiClient.searchBooks(bookName);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.INQUERY_SUCCESS,books));
    }

    // 책 상세 페이지 진입 api
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<BookResponseDto>> getBookDetail(@RequestParam String isbn){
        BookResponseDto bookResponse = bookService.getAndSaveBooks(isbn);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.INQUERY_SUCCESS,bookResponse));
    }

    //카테고리별 책 리스트 조회 api

    //베스트 셀러
//    @GetMapping("/bestsellers")
//    public ResponseEntity<List<Book>> getBestSellers() {
//        List<Book> bestSellers = bookApiClient.getBestSellerBooks();
//        return ResponseEntity.ok(bestSellers);
//    }

}
