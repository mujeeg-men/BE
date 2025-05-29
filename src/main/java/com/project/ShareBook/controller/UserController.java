package com.project.ShareBook.controller;

import com.project.ShareBook.Entity.User;
import com.project.ShareBook.common.ApiResponse;
import com.project.ShareBook.common.SuccessType;
import com.project.ShareBook.dto.BookSaveDto;
import com.project.ShareBook.dto.UserRequestDto;
import com.project.ShareBook.dto.UserResponseDto;
import com.project.ShareBook.jwt.CustomUserDetails;
import com.project.ShareBook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UserResponseDto>> save(@RequestBody UserRequestDto userRequestDto){
        User user = userService.userSave(userRequestDto);
        UserResponseDto responseDto = new UserResponseDto(user);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.CREATE_SUCCESS,responseDto));
    }

    //회원 프로필 get api
    @GetMapping("")
    public ResponseEntity<ApiResponse<UserResponseDto>> get(@AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        UserResponseDto responseDto = userService.getUser(user);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.INQUERY_SUCCESS,responseDto));
    }


}
