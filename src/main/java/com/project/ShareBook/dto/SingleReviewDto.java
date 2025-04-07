package com.project.ShareBook.dto;

import com.project.ShareBook.Entity.BookReview;
import jakarta.persistence.Lob;

public class SingleReviewDto {
    Long reviewId;
    Long userId;
    Long bookId;
    Double rate;
    @Lob
    String reviewText;
    Boolean isPublic;


    public SingleReviewDto(BookReview bookReview) {
        this.reviewId = bookReview.getId();
        this.bookId = bookReview.getBook().getId();
        this.userId = bookReview.getUser().getId();
        this.rate = bookReview.getRate();
        this.reviewText = bookReview.getReviewText();
        this.isPublic = bookReview.getIsPublic();
    }
}
