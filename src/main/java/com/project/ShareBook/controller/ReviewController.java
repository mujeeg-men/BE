package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.ReviewRequestDto;
import com.project.ShareBook.dto.ReviewResponseDto;
import com.project.ShareBook.dto.SingleReviewDto;
import com.project.ShareBook.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/save")
    public ResponseEntity<SingleReviewDto> reviewCreate(@RequestBody ReviewRequestDto request){
        SingleReviewDto singleReviewDto = reviewService.reviewCreate(request);
        return ResponseEntity.status(HttpStatus.OK).body(singleReviewDto);
    }
    //책 아이디로 리뷰 찾기 api
    @GetMapping("/book/{bookId}")
    public ResponseEntity<ReviewResponseDto>  reviewSelectByBook(@PathVariable Long bookId){
        ReviewResponseDto reviewSelectByBookId = reviewService.reviewSelectByBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(reviewSelectByBookId);
    }
    //유저가 쓴 리뷰 get api
    @GetMapping("/user/{userId}")
    public ResponseEntity<ReviewResponseDto>  reviewSelectByUser(@PathVariable Long userId){
        ReviewResponseDto reviewSelectByBookId = reviewService.reviewSelectByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(reviewSelectByBookId);
    }

    //리뷰 삭제
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, @RequestParam Long userId){
        reviewService.softDeleteReview(reviewId,userId);
        return ResponseEntity.ok("리뷰가 삭제되었습니다");
    }

    //리뷰 수정



}
