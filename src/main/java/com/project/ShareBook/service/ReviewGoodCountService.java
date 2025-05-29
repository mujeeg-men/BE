package com.project.ShareBook.service;

import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.ReviewGoodCount;
import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.ReviewResponseDto;
import com.project.ShareBook.repository.ReviewGoodCountRepository;
import com.project.ShareBook.repository.ReviewRepository;
import com.project.ShareBook.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewGoodCountService {

    private final ReviewGoodCountRepository reviewGoodCountRepository;
    private final ReviewRepository bookReviewRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long addGoodCount(Long userId, Long reviewId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        BookReview review = bookReviewRepository.findById(reviewId)
            .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        // 이미 좋아요한 기록이 있는지 확인
        Optional<ReviewGoodCount> existing = reviewGoodCountRepository.findByUserAndReview(user, review);

        if (existing.isPresent()) {
            // 좋아요 취소
            review.getGoodCounts().remove(existing.get());
            reviewGoodCountRepository.delete(existing.get());
        }else {
            // 좋아요 추가
            ReviewGoodCount newLike = new ReviewGoodCount(user, review);
            review.getGoodCounts().add(newLike);
            reviewGoodCountRepository.save(newLike);
        }

        return (long) review.getGoodCounts().size(); // 실시간 좋아요 수 반환
    }

    //내가 좋아요 누른 리뷰 뽑기
    public ReviewResponseDto getReviewByGoodCount(Long userId){
        List<ReviewGoodCount> likedReviews = reviewGoodCountRepository.findByUserId(userId);
        List<BookReview> reviews = likedReviews.stream()
            .map(ReviewGoodCount::getReview)
            .collect(Collectors.toList());

        return new ReviewResponseDto(reviews);
    }
}
