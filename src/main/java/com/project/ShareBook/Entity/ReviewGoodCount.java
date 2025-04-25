package com.project.ShareBook.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "review_id"})
})
public class ReviewGoodCount extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    @JsonIgnore
    private BookReview review;
    
    private Long goodCount;

    // 좋아요 증가 메서드
    public void increaseGoodCount() {
        this.goodCount += 1;
    }

    // 생성자 추가
    public ReviewGoodCount(User user, BookReview review) {
        this.user = user;
        this.review = review;
        this.goodCount = 1L; // 처음 좋아요를 누르면 1부터 시작
    }
}
