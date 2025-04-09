package com.project.ShareBook.jwt;

import com.project.ShareBook.Entity.User;
import com.project.ShareBook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // 단순 throw 시키는 이유는 CustomAuthenticationEntryPoint 로 흘려보내기 위해서
        User user = userRepository.findByUserEmail(email).orElseThrow();

        return new CustomUserDetails(user);
    }
}
