package com.project.ShareBook.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReviewRequestDto {
    Long userId;
    Long bookId;

    Double rate;
    @Lob
    String reviewText;
    Boolean isPublic;


}
