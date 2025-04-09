package com.project.ShareBook.dto;

import com.project.ShareBook.Entity.BookReview;
import jakarta.persistence.Lob;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Data
@Getter
public class ReviewResponseDto {

    private List<SingleReviewDto> reviews;

    public ReviewResponseDto(List<BookReview> bookReviews) {
        this.reviews = bookReviews.stream()
            .map(SingleReviewDto::new)
            .collect(Collectors.toList());
    }

}
