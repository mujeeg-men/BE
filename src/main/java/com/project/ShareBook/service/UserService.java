package com.project.ShareBook.service;

import com.project.ShareBook.Entity.User;
import com.project.ShareBook.dto.UserRequestDto;
import com.project.ShareBook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User userSave(UserRequestDto request){
        User user = User.builder()
            .userAddress(request.getUserAddress())
            .userEmail(request.getUserEmail())
            .userName(request.getUserName())
            .userPassword(request.getUserPassword())
            .userBirth(request.getUserBirth())
            .userGender(request.getUserGender())
            .userPhone(request.getUserPhone())
            .userInterest(request.getUserInterest())
            .userNickname(request.getUserNickname())
            .userTerms(request.isUserTerms()
        ).build();
        User save = userRepository.save(user);
        return save;
    }
}
