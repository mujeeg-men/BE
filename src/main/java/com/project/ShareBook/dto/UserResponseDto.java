package com.project.ShareBook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.ShareBook.Entity.Enum.Gender;
import com.project.ShareBook.Entity.User;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String userEmail;
    private String userNickname;

    public UserResponseDto(User user) {
        this.userEmail = user.getUserEmail();
        this.userNickname = user.getUserNickname();
    }
}
