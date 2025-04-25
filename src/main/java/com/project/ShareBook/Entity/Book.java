package com.project.ShareBook.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Book extends BaseEntity{

    String bookName;
    String bookCategory;
    @Lob
    String bookUrl;
    String bookIsbn;

    @Lob
    @Column(columnDefinition = "TEXT")
    String bookDescription;
    LocalDateTime bookPublishingDate;
    Long bookPrice;

    @Lob
    String bookImageUrl;
    String bookPublisher;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaveBook> saveBooks = new ArrayList<>();
}
