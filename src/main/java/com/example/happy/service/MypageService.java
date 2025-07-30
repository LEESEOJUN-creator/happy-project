package com.example.happy.service;

import com.example.happy.converter.MypageConverter;
import com.example.happy.domain.User;
import com.example.happy.dto.MypageResponseDto;
import com.example.happy.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final UserRepository userRepository;
    private final MypageConverter mypageConverter;
    private final HttpSession session;

    public MypageResponseDto getMyInfo() {
        User user = getSessionUser();
        return mypageConverter.tomypageResponse(user);
    }

    private User getSessionUser() {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return user;
    }
}
