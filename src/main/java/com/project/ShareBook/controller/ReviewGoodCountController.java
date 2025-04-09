package com.project.ShareBook.controller;

import com.project.ShareBook.service.ReviewGoodCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review/good")
@RequiredArgsConstructor
public class ReviewGoodCountController {
    private final ReviewGoodCountService reviewGoodCountService;

    @PostMapping("/{userId}/{reviewId}")
    public ResponseEntity<Long> addGoodCount(@PathVariable Long userId, @PathVariable Long reviewId) {
        Long updatedGoodCount = reviewGoodCountService.addGoodCount(userId, reviewId);
        return ResponseEntity.ok(updatedGoodCount);
    }
    //내가 좋아요 누른 리뷰 목록 api
}
