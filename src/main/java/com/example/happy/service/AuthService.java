package com.example.happy.service;

import com.example.happy.converter.UserConverter;
import com.example.happy.domain.User;
import com.example.happy.dto.request.LoginRequestDto;
import com.example.happy.dto.request.SignupRequestDto;
import com.example.happy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    /* 회원가입 */
    public void signup(SignupRequestDto dto, MultipartFile studentCardImage) {

        // 1. 중복 이메일
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        // 2. 비밀번호 암호화
        String encodedPwd = passwordEncoder.encode(dto.getPassword());

        // 3. 학생증 이미지 저장 (없어도 OK)
        String savedUrl = saveImage(studentCardImage);   // null or "/uploads/…"

        // 4. 엔티티 생성 & 저장 (초기 verified = false)
        User user = userConverter.toUser(dto, encodedPwd, savedUrl);
        userRepository.save(user);
    }

    /* 로그인 – 성공 시 User 리턴 */
    public User login(LoginRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (user.isVerified()) {
            throw new IllegalStateException("학생증 인증 대기 중입니다.");
        }

        return user;      // 컨트롤러에서 세션에 넣음
    }

    /* 이미지 저장 유틸 */
    private String saveImage(MultipartFile file) {

        if (file == null || file.isEmpty()) return null;

        try {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) dir.mkdirs();

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(dir, fileName);
            file.transferTo(dest);

            return "/uploads/" + fileName;    // ★ 프론트에서 GET 가능 경로
        } catch (IOException e) {
            throw new RuntimeException("학생증 이미지 업로드 실패", e);
        }
    }
}


