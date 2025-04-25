package com.project.ShareBook.dto.review;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ReviewUpdateRequestDto {
    Double rate;
    @Lob
    String reviewText;
    Boolean isPublic;
}
