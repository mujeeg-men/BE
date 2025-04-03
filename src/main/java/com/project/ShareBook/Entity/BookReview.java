package com.project.ShareBook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookReview extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book bookId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    Double rate;
    @Lob
    String reviewText;
    Boolean isPublic;


}
