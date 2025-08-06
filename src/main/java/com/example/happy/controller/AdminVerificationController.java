package com.example.happy.controller;

import com.example.happy.domain.VerificationRequest;
import com.example.happy.service.AdminVerificationService;
import com.example.happy.service.AdminVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/verifications")
@Tag(name = "Admin Verification API", description = "학생증 인증 요청 관련 관리자 API")
public class AdminVerificationController {

    private final AdminVerificationService verificationService;

    @Operation(summary = "대기 중인 인증 요청 목록 조회")
    @GetMapping
    public ResponseEntity<List<VerificationRequest>> getPendingRequests() {
        return ResponseEntity.ok(verificationService.getPendingRequests());
    }

    @Operation(summary = "인증 요청 승인")
    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        verificationService.approveRequest(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "인증 요청 거절")
    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable Long id) {
        verificationService.rejectRequest(id);
        return ResponseEntity.ok().build();
    }
}

