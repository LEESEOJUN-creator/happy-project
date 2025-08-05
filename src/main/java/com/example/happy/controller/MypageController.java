package com.example.happy.controller;

import com.example.happy.dto.response.MypageResponseDto;
import com.example.happy.dto.request.PasswordUpdateRequestDto;
import com.example.happy.service.MypageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;


    @GetMapping("/me")
    public ResponseEntity<MypageResponseDto> getMyInfo() {
        return ResponseEntity.ok(mypageService.getMyInfo());
    }


    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordUpdateRequestDto dto) {
        mypageService.changePassword(dto);
        return ResponseEntity.noContent().build();
    }
}
