package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.ReviewRequestDto;
import com.project.ShareBook.dto.ReviewResponseDto;
import com.project.ShareBook.dto.SingleReviewDto;
import com.project.ShareBook.dto.review.ReviewUpdateRequestDto;
import com.project.ShareBook.jwt.CustomUserDetails;
import com.project.ShareBook.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<SingleReviewDto> reviewCreate(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestBody ReviewRequestDto request){
        Long id = userDetails.getUser().getId();
        SingleReviewDto singleReviewDto = reviewService.reviewCreate(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(singleReviewDto);
    }
    //책 아이디로 리뷰 찾기 api
    @GetMapping("/book/{bookId}")
    public ResponseEntity<ReviewResponseDto>  reviewSelectByBook(@PathVariable Long bookId){
        ReviewResponseDto reviewSelectByBookId = reviewService.reviewSelectByBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(reviewSelectByBookId);
    }
    //유저가 쓴 리뷰 get api
    @GetMapping("/user")
    public ResponseEntity<ReviewResponseDto> reviewSelectByUser(@AuthenticationPrincipal CustomUserDetails userDetails){
        Long id = userDetails.getUser().getId();
        ReviewResponseDto reviewSelectByBookId = reviewService.reviewSelectByUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(reviewSelectByBookId);
    }

    //리뷰 삭제
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        reviewService.softDeleteReview(reviewId,user);
        return ResponseEntity.ok("리뷰가 삭제되었습니다");
    }

//    리뷰 수정
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<?> updateReview(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        ReviewUpdateRequestDto reviewUpdateRequestDto,
        Long reviewId){
        Long userId = userDetails.getUser().getId();
        reviewService.updateReview(userId, reviewId, reviewUpdateRequestDto);
        return ResponseEntity.ok("수정완료");
    }

    //책마다 리뷰 별점 평균 반환 API
    @GetMapping("/rate/{bookId}")
    public ResponseEntity<Double> rateAverage(@PathVariable Long bookId){
        Double rateAverage = reviewService.rateAverage(bookId);
        return ResponseEntity.ok(rateAverage);
    }


}
