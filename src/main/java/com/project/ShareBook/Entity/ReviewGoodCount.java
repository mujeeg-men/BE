package com.project.ShareBook.Entity;

import com.project.ShareBook.dto.ReviewRequestDto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ReviewGoodCount {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private BookReview reviewId;
    
    private Long goodCount;
}
