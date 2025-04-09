package com.project.ShareBook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.ShareBook.Entity.Enum.Gender;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDto {
    String userName;
    String userEmail;
    String userPassword;
    String userPhone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate userBirth;
    String userAddress;
    Gender userGender;
    String userInterest;
    String userNickname;
    boolean userTerms;
}
