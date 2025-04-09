package com.project.ShareBook.dto;

import com.project.ShareBook.Entity.Book;
import jakarta.persistence.Lob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class AladinBookItem {

    private String title;
    private String link;
    private String author;
    private String pubDate;

    @Lob
    private String description;

    private String isbn;
    private String isbn13;
    private Long priceSales;
    private Long priceStandard;
    private String cover;
    private String categoryName;
    private String publisher;

    public Book toBook() {
        return Book.builder()
            .bookName(title)
            .bookCategory(categoryName)
            .bookUrl(link)
            .bookIsbn(isbn13)  // ✅ ISBN13 사용
            .bookDescription(description)
            .bookPublishingDate(parsePubDate(pubDate))  // ✅ 날짜 변환
            .bookPrice(priceStandard)
            .bookImageUrl(cover)
            .bookPublisher(publisher)
            .build();
    }

    // ✅ pubDate (문자열) → LocalDateTime 변환
    private LocalDateTime parsePubDate(String pubDate) {
        if (pubDate == null || pubDate.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(pubDate, formatter).atStartOfDay();
    }
}
