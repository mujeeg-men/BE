package com.project.ShareBook.controller;

import com.project.ShareBook.dto.ReviewResponseDto;
import com.project.ShareBook.jwt.CustomUserDetails;
import com.project.ShareBook.service.ReviewGoodCountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goodCount")
@RequiredArgsConstructor
public class ReviewGoodCountController {
    private final ReviewGoodCountService reviewGoodCountService;

    @PostMapping("/{reviewId}")
    public ResponseEntity<Long> addGoodCount(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long reviewId) {
        Long userId = userDetails.getUser().getId();
        Long updatedGoodCount = reviewGoodCountService.addGoodCount(userId, reviewId);
        return ResponseEntity.ok(updatedGoodCount);
    }

    //내가 좋아요 누른 리뷰 목록 api
    @GetMapping("")
        public ResponseEntity<?> getGoodReviewByUser(@AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        ReviewResponseDto responseDto = reviewGoodCountService.getReviewByGoodCount(userId);
        return ResponseEntity.ok(responseDto);
        }
    }

