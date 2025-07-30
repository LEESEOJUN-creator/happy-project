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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    public void signup(SignupRequestDto dto, MultipartFile studentCardImage) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        String studentCardUrl = null;
        if (studentCardImage != null && !studentCardImage.isEmpty()) {
            try {
                // 절대 경로로 설정
                String projectPath = System.getProperty("user.dir");
                String uploadDir = projectPath + "/uploads/";
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs(); // uploads 폴더 생성
                }

                String fileName = UUID.randomUUID() + "_" + studentCardImage.getOriginalFilename();
                File dest = new File(uploadDir + fileName);

                studentCardImage.transferTo(dest);
                studentCardUrl = "/uploads/" + fileName; // 프론트에서 접근 가능한 경로

            } catch (IOException e) {
                throw new RuntimeException("학생증 이미지 업로드 실패", e);
            }
        }

        User user = userConverter.toUser(dto, encodedPassword, studentCardUrl);
        userRepository.save(user);
    }

    public User login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        if (!user.isVerified()) {
            throw new RuntimeException("학생증 인증 대기중입니다. 로그인 불가!");
        }

        return user;
    }
}

