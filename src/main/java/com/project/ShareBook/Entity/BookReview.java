package com.project.ShareBook.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ShareBook.dto.log.LogUpdateRequestDto;
import com.project.ShareBook.dto.review.ReviewUpdateRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
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
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    Double rate;

    @Lob
    String reviewText;
    Boolean isPublic;
    Boolean isDeleted = false;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewGoodCount> goodCounts = new ArrayList<>();

    public void update(ReviewUpdateRequestDto request) {
        if (request.getRate() != null) {
            this.rate = request.getRate();
        }
        if (request.getReviewText() != null) {
            this.reviewText = request.getReviewText();
        }
        if (request.getIsPublic() !=null){
            this.isPublic = request.getIsPublic();
        }
    }
}
