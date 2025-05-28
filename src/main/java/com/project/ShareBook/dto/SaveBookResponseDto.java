package com.project.ShareBook.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveBookResponseDto {
    private String bookName;
    private String author;
    private String bookIsbn;
    private String bookImageUrl;
    private LocalDateTime readAt;
}
