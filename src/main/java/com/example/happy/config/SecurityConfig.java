package com.example.happy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /* ────────── 비밀번호 암호화 ────────── */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* ────────── Spring Security 기본 설정 ────────── */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        /* 개발 단계에서는 모든 요청 허용, 추후 필요한 엔드포인트만 열어주세요 */
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    /* ────────── CORS 설정 ────────── */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();

        /* 1) 허용할 Origin(프론트 주소) 지정
              - 로컬 개발: http://localhost:3000
              - Vite(5173 포트) 사용 시 둘 다 열어두면 편리
              - 운영 배포 시에는 실제 프론트 도메인으로 교체 */
        cfg.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://localhost:5173"
                // "https://www.front-domain.com"   // ← 배포용
        ));

        /* 2) 메서드 & 헤더 허용 */
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
        cfg.setExposedHeaders(List.of("Location")); // 필요 시 추가로 노출

        /* 3) 세션/쿠키 전송 허용 */
        cfg.setAllowCredentials(true);

        /* 4) 프리플라이트 결과 캐싱 시간(초) */
        cfg.setMaxAge(3600L);

        /* 5) 매핑 경로 지정 — 전체(API만 열어두려면 "/api/**") */
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}
