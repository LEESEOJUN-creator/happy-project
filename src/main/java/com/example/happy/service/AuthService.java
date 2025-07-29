package com.example.happy.service;

import com.example.happy.converter.UserConverter;
import com.example.happy.domain.User;
import com.example.happy.dto.request.LoginRequestDto;
import com.example.happy.dto.request.SignupRequestDto;
import com.example.happy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    public void signup(SignupRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = userConverter.toUser(dto, encodedPassword);
        userRepository.save(user);
    }
    public User login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("가입되지 않은 이메일입니다."));
        boolean match = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if (!match) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        if (!user.isVerified()) {
            throw new RuntimeException("학생증 인증 대기중입니다. 로그인 불가!");
        }
        return user;



    }


}
