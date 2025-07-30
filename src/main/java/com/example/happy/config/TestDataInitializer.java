package com.example.happy.config;

import com.example.happy.domain.Post;
import com.example.happy.domain.User;
import com.example.happy.repository.PostRepository;
import com.example.happy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void run(String... args) {

        User user = User.builder()
                .email("test@example.com")
                .password("password123")
                .username("tester")
                .verified(true)
                .build();

        userRepository.save(user);

        Post post = Post.builder()
                .author(user)
                .title("샘플 게시글")
                .content("테스트 내용입니다.")
                .build();

        postRepository.save(post);
    }
}
