package com.project.ShareBook.controller;

import com.project.ShareBook.dto.LoginRequestDto;
import com.project.ShareBook.dto.LoginResponseDto;
import com.project.ShareBook.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final  AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
        LoginResponseDto login = authService.login(request);
        return ResponseEntity.ok(login);
    }
}
