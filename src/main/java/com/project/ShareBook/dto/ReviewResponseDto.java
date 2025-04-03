package com.project.ShareBook.dto;

import com.project.ShareBook.Entity.BookReview;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
@Getter
public class ReviewResponseDto {
    Long reviewId;
    Long userId;
    Long bookId;
    Double rate;
    @Lob
    String reviewText;
    Boolean isPublic;


    public ReviewResponseDto(BookReview bookReview) {
        this.reviewId = bookReview.getId();
        this.bookId = bookReview.getBookId().getId();
        this.userId = bookReview.getUserId().getId();
        this.rate = bookReview.getRate();
        this.reviewText = bookReview.getReviewText();
        this.isPublic = bookReview.getIsPublic();
    }

}
