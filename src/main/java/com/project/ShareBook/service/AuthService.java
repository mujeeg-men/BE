package com.project.ShareBook.service;

import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.LoginRequestDto;
import com.project.ShareBook.dto.LoginResponseDto;
import com.project.ShareBook.jwt.JWTProvider;
import com.project.ShareBook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByUserEmail(request.getUserEmail())
            .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        if (!user.getUserPassword().equals(request.getUserPassword())) {
            throw new IllegalArgumentException("비밀번호 틀림");
        }

        String token = jwtProvider.generateToken(
            "access",
            user.getUserEmail(),
            1000L * 60 * 60 // 1시간짜리
        );

        return new LoginResponseDto(user.getUserEmail(),token);
    }

}
