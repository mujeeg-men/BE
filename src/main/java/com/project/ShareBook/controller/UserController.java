package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.BookSaveDto;
import com.project.ShareBook.dto.UserRequestDto;
import com.project.ShareBook.dto.UserResponseDto;
import com.project.ShareBook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/save")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto userRequestDto){
        User user = userService.userSave(userRequestDto);
        UserResponseDto responseDto = new UserResponseDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


}
