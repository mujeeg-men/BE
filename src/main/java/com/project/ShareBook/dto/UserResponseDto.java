package com.project.ShareBook.dto;

import com.project.ShareBook.Entity.Enum.Gender;
import com.project.ShareBook.Entity.User;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
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

    public UserResponseDto(User user) {
        this.userAddress = user.getUserAddress();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.userBirth = user.getUserBirth();
        this.userGender = user.getUserGender();
        this.userPhone = user.getUserPhone();
        this.userInterest = user.getUserInterest();
        this.userNickname = user.getUserNickname();
        this.userTerms = user.isUserTerms();
    }
}
