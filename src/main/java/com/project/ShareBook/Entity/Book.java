package com.project.ShareBook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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

}
