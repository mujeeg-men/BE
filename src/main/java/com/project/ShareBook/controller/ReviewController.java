package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.dto.ReviewRequestDto;
import com.project.ShareBook.dto.ReviewResponseDto;
import com.project.ShareBook.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/save")
    public ResponseEntity<ReviewResponseDto> reviewCreate(@RequestBody ReviewRequestDto request){
        ReviewResponseDto bookReview = reviewService.reviewCreate(request);
        return ResponseEntity.status(HttpStatus.OK).body(bookReview);
    }

}
