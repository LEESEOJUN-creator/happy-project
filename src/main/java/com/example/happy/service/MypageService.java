package com.example.happy.service;

import com.example.happy.converter.MypageConverter;
import com.example.happy.domain.User;
import com.example.happy.dto.response.MypageResponseDto;
import com.example.happy.dto.request.PasswordUpdateRequestDto;
import com.example.happy.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class MypageService {

    private final UserRepository userRepository;
    private final MypageConverter mypageConverter;
    private final HttpSession session;
    private final PasswordEncoder passwordEncoder;

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
    @Transactional
    public void changePassword(PasswordUpdateRequestDto dto) {
        // 1) 세션 사용자 확인
        User sessionUser = getSessionUser();

        // 2) 항상 DB에서 최신 사용자 재조회 (세션 객체가 오래됐을 수 있음)
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new IllegalStateException("회원 정보를 찾을 수 없습니다."));

        // 3) 현재 비밀번호 검증
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 4) 새 비밀번호가 이전과 동일하면 거절
        if (passwordEncoder.matches(dto.getNewPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이전과 동일한 비밀번호로 변경할 수 없습니다.");
        }

        // 5) 암호화 저장 (변경감지로 update)
        user.changePassword(passwordEncoder.encode(dto.getNewPassword()));

        // 6) 보안상 세션 무효화 → 프론트에서 재로그인 유도
        session.invalidate();
    }



}
