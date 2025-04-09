package com.project.ShareBook.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.ShareBook.Entity.Enum.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

//    @OneToMany(mappedBy = "user")
//    private List<SaveBook> readBooks = new ArrayList<>();

}
