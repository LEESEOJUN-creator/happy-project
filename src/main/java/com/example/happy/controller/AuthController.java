package com.example.happy.controller;

import com.example.happy.domain.User;
import com.example.happy.dto.request.LoginRequestDto;
import com.example.happy.dto.request.SignupRequestDto;
import com.example.happy.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> signup(
            @Valid @ModelAttribute SignupRequestDto dto,
            @RequestPart(required = false) MultipartFile studentCardImage
    ) {
        authService.signup(dto, studentCardImage);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto, HttpSession session){
        User user = authService.login(dto);
        session.setAttribute("loginUser", user);  // 세션에 유저 저장
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공");
    }

}
