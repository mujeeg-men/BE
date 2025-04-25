package com.project.ShareBook.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.ShareBook.Entity.Enum.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
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
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class User extends BaseEntity {
    String userName;
    String userEmail;
    String userPassword;
    String userPhone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate userBirth;
    String userAddress;
    @Enumerated(EnumType.STRING)
    Gender userGender;
    String userInterest;
    String userNickname;
    boolean userTerms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaveBook> saveBooks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewGoodCount> reviewGoodCounts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReadingLog> readingLogs  = new ArrayList<>();

}
