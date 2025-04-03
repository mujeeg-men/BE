package com.project.ShareBook.service;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.ReviewRequestDto;
import com.project.ShareBook.dto.ReviewResponseDto;
import com.project.ShareBook.repository.BookRepository;
import com.project.ShareBook.repository.ReviewRepository;
import com.project.ShareBook.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService{
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public ReviewResponseDto reviewCreate(ReviewRequestDto request){

        Book book= bookRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다"));
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));

        // 2. 리뷰 엔티티 생성 및 저장
        BookReview review = BookReview.builder()
            .bookId(book)
            .userId(user)
            .rate(request.getRate())
            .reviewText(request.getReviewText())
            .isPublic(request.getIsPublic())
            .build();

        reviewRepository.save(review);

        // 3. 저장된 리뷰를 DTO로 변환하여 반환
        return new ReviewResponseDto(review);

    }
    public ReviewResponseDto reviewSelectByBookId(Book bookId){
//        Book book = bookRepository.findById(bookId)
//            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다"));
        BookReview byBookId = reviewRepository.findByBookId(bookId);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto(byBookId);
        log.info(String.valueOf(reviewResponseDto));
        return reviewResponseDto;
    }

}
