package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.User;
import com.project.ShareBook.common.ApiResponse;
import com.project.ShareBook.common.SuccessType;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<SingleReviewDto>> reviewCreate(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestBody ReviewRequestDto request){
        Long id = userDetails.getUser().getId();
        SingleReviewDto singleReviewDto = reviewService.reviewCreate(id,request);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.CREATE_SUCCESS,singleReviewDto));
    }

    //책 아이디로 리뷰 찾기 api
    @GetMapping("/book/{bookId}")
    public ResponseEntity<ApiResponse<ReviewResponseDto>>  reviewSelectByBook(@PathVariable Long bookId){
        ReviewResponseDto reviewSelectByBookId = reviewService.reviewSelectByBook(bookId);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.INQUERY_SUCCESS,reviewSelectByBookId));
    }

    //유저가 쓴 리뷰 get api
    @GetMapping("/user")
    public ResponseEntity<ApiResponse<ReviewResponseDto>> reviewSelectByUser(@AuthenticationPrincipal CustomUserDetails userDetails){
        Long id = userDetails.getUser().getId();
        ReviewResponseDto reviewSelectByBookId = reviewService.reviewSelectByUser(id);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.INQUERY_SUCCESS,reviewSelectByBookId));
    }

    //리뷰 삭제
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        reviewService.softDeleteReview(reviewId,user);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.DELETE_SUCCESS,"리뷰가 삭제 되었습니다"));
    }

//    리뷰 수정
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateReview(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        ReviewUpdateRequestDto reviewUpdateRequestDto,
        Long reviewId){
        Long userId = userDetails.getUser().getId();
        reviewService.updateReview(userId, reviewId, reviewUpdateRequestDto);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.UPDATE_SUCCESS
            ,"리뷰가 수정 되었습니다"));
    }

    //책마다 리뷰 별점 평균 반환 API
    @GetMapping("/rate/{bookId}")
    public ResponseEntity<ApiResponse<Double>> rateAverage(@PathVariable Long bookId){
        Double rateAverage = reviewService.rateAverage(bookId);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.INQUERY_SUCCESS,rateAverage));
    }


}
