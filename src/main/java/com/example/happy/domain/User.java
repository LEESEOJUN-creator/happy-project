package com.example.happy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "user_id")
    private Long id;  // 사용자 고유 ID

    @Column(nullable = false, unique = true) // null값 허용x, 중복 불가
    private String email;  // 로그인 ID

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    @Column(nullable = false)
    private String username; // 닉네임 or 이름

    @Column(length = 255)
    private String studentCardUrl;

    @CreationTimestamp
    private LocalDateTime createdAt; // 가입일

    private boolean verified; // 학생증 인증 여부 (false면 로그인 불가)

    public void verify() {
        this.verified = true;
    }
    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}

