package com.project.ShareBook.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReadingLog  extends  BaseEntity{

    LocalDate date; // 기록 날짜

    Integer pageRead; // 읽은 페이지 수

    String comment; // 소감문

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "save_book_id")
    @JsonIgnore
    SaveBook saveBook;

}
