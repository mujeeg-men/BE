package com.project.ShareBook.service;

import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.UserRequestDto;
import com.project.ShareBook.dto.UserResponseDto;
import com.project.ShareBook.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User userSave(UserRequestDto request){
        if(userRepository.findByUserEmail(request.getUserEmail()).isPresent()){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다");
        }
        // 🔐 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(request.getUserPassword());
        User user = User.builder()
            .userAddress(request.getUserAddress())
            .userEmail(request.getUserEmail())
            .userName(request.getUserName())
            .userPassword(encryptedPassword)
            .userBirth(request.getUserBirth())
            .userGender(request.getUserGender())
            .userPhone(request.getUserPhone())
            .userInterest(request.getUserInterest())
            .userNickname(request.getUserNickname())
            .userTerms(request.isUserTerms()
        ).build();
        return userRepository.save(user);
    }
    public UserResponseDto getUser(User user){
        return new UserResponseDto(user);
    }
}
