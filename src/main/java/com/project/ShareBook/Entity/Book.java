package com.project.ShareBook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
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
    String bookDescription;
    LocalDateTime bookPublishingDate;
    Long bookPrice;

    @Lob
    String bookImageUrl;
    String bookPublisher;

//    @OneToMany(mappedBy = "book")
//    private List<SaveBook> readers = new ArrayList<>();

}
