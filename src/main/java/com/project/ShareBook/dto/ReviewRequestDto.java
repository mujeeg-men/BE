package com.project.ShareBook.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewRequestDto {
    Long userId;
    Long bookId;

    Double rate;
    @Lob
    String reviewText;
    Boolean isPublic;


}
