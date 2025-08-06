package com.example.happy.service;

import com.example.happy.domain.User;
import com.example.happy.domain.VerificationRequest;
import com.example.happy.enums.VerificationStatus;
import com.example.happy.repository.UserRepository;
import com.example.happy.repository.VerificationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminVerificationService {

    private final VerificationRequestRepository verificationRepo;
    private final UserRepository userRepository;

    public List<VerificationRequest> getPendingRequests() {
        return verificationRepo.findAllByStatus(VerificationStatus.PENDING);
    }

    public void approveRequest(Long id) {
        VerificationRequest request = verificationRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청 없음"));
        request.setStatus(VerificationStatus.APPROVED);

        User user = request.getUser();
        user.verify();

        verificationRepo.save(request);
    }

    public void rejectRequest(Long id) {
        VerificationRequest request = verificationRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청 없음"));
        request.setStatus(VerificationStatus.REJECTED);

        User user = request.getUser();

        verificationRepo.delete(request);
        userRepository.delete(user);
    }
}