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
    String userEmail;
    String userPassword;
    String userNickname;

}
