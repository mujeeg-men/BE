package com.project.ShareBook.dto;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.Entity.BookReview;
import com.project.ShareBook.Entity.ReadingLog;
import com.project.ShareBook.dto.log.LogResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponseDto {
    Long id;

    String bookName;

    String bookCategory;

    String bookUrl;

    String bookIsbn;

    String bookDescription;

    LocalDateTime bookPublishingDate;

    Long bookPrice;

    String bookImageUrl;

    String bookPublisher;

    List<BookReview> bookReview;

    public static BookResponseDto from(Book book) {
        return BookResponseDto.builder()
            .id(book.getId())
            .bookName(book.getBookName()) // 관계 매핑 필요
            .bookCategory(book.getBookCategory())
            .bookUrl(book.getBookUrl())
            .bookIsbn(book.getBookIsbn())
            .bookDescription(book.getBookDescription())
            .bookPublishingDate(book.getBookPublishingDate())
            .bookPrice(book.getBookPrice())
            .bookImageUrl(book.getBookImageUrl())
            .bookPublisher(book.getBookPublisher())
            .bookReview(book.getReviews())
            .build();
    }
}
