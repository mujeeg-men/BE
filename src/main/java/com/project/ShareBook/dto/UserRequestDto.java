package com.project.ShareBook.dto;

import com.project.ShareBook.Entity.Enum.Gender;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRequestDto {
    String userName;
    String userEmail;
    String userPassword;
    String userPhone;
    Date userBirth;
    String userAddress;
    Gender userGender;
    String userInterest;
    String userNickname;
    boolean userTerms;
}
