package com.project.ShareBook.service;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.ReviewRequestDto;
import com.project.ShareBook.dto.ReviewResponseDto;
import com.project.ShareBook.dto.SingleReviewDto;
import com.project.ShareBook.repository.BookRepository;
import com.project.ShareBook.repository.ReviewRepository;
import com.project.ShareBook.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService{
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public SingleReviewDto reviewCreate(ReviewRequestDto request){

        Book book= bookRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다"));
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));

        // 2. 리뷰 엔티티 생성 및 저장
        BookReview review = BookReview.builder()
            .book(book)
            .user(user)
            .rate(request.getRate())
            .reviewText(request.getReviewText())
            .isPublic(request.getIsPublic())
            .build();

        reviewRepository.save(review);

        // 3. 저장된 리뷰를 DTO로 변환하여 반환
        return new SingleReviewDto(review);

    }
    @Transactional
    public ReviewResponseDto reviewSelectByBook(Long bookId){
        List<BookReview> byBookId = reviewRepository.findByBookIdAndIsDeletedFalse(
            bookId);

        return new ReviewResponseDto(byBookId);
    }
    @Transactional
    public ReviewResponseDto reviewSelectByUser(Long userId){
        List<BookReview> byUserId = reviewRepository.findByUserIdAndIsDeletedFalse(userId);
        return new ReviewResponseDto(byUserId);
    }
    //진짜 DB 삭제
    @Transactional
    public void deleteReview(Long reviewId, Long userId){
        BookReview review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));

        if (!review.getUser().getId().equals(userId)) {
            throw new SecurityException("해당 리뷰를 삭제할 권한이 없습니다.");
        }

        reviewRepository.deleteById(reviewId);
    }
    //soft delete 사용 api
    @Transactional
    public void softDeleteReview(Long reviewId, Long userId) {
        BookReview review = reviewRepository.findByIdAndUserId(reviewId, userId)
            .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));
        log.info("---------------------------찾은 리뷰 : {}",review.getId());
        review.setIsDeleted(true);
        // updatedAt 같은 필드도 자동으로 업데이트됨
    }
    @Transactional
    public Double rateAverage(Long bookId){
        Book book = bookRepository.findById(bookId)
            .orElseThrow(()->new IllegalArgumentException("존재하지 않는 책입니다"));
        List<BookReview> reviews = reviewRepository.findByBookAndIsDeletedFalse(book);

        if (reviews.isEmpty()) {
            return 0.0; // 또는 null 반환도 가능 (프론트 처리 방식에 따라)
        }

        double avg = reviews.stream()
            .mapToDouble(BookReview::getRate)
            .average()
            .orElse(0.0); // 혹시라도 에러 방지를 위한 디폴트

        return avg;

    }

}
