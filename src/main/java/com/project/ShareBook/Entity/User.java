package com.project.ShareBook.Entity;

import com.project.ShareBook.Entity.Enum.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import java.util.Date;
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
    Date userBirth;
    String userAddress;
    @Enumerated(EnumType.STRING)
    Gender userGender;
    String userInterest;
    String userNickname;
    boolean userTerms;

}
