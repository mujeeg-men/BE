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

//        // 이미 좋아요를 눌렀는지 확인
//        BookReview reviews = bookReviewRepository.findById(reviewId)
//            .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다.")); // ✅ reviewId -> BookReview 객체로 변환

        ReviewGoodCount reviewGoodCount = reviewGoodCountRepository.findByUserAndReview(user,
                review)
            .orElse(null);

        if (reviewGoodCount == null) {
            reviewGoodCount = new ReviewGoodCount(user, review);
            reviewGoodCountRepository.save(reviewGoodCount); // ✅ insert 발생
        } else {
            reviewGoodCount.setGoodCount(reviewGoodCount.getGoodCount() + 1);
            reviewGoodCountRepository.save(reviewGoodCount); // ✅ update 발생
        }
        Long total = reviewGoodCountRepository.getTotalGoodCountByReviewId(reviewId);
        return total != null ? total : 0L;
//        return reviewGoodCount.getGoodCount();
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
