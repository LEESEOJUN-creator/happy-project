package com.example.happy.repository;

import com.example.happy.domain.VerificationRequest;
import com.example.happy.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VerificationRequestRepository extends JpaRepository<VerificationRequest, Long> {
    List<VerificationRequest> findAllByStatus(VerificationStatus status);
}
