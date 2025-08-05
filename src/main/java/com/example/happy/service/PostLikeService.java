package com.example.happy.service;

import com.example.happy.domain.Post;
import com.example.happy.domain.PostLike;
import com.example.happy.domain.User;
import com.example.happy.repository.PostLikeRepository;
import com.example.happy.repository.PostRepository;
import com.example.happy.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final UserRepository     userRepository;
    private final PostRepository     postRepository;

    /* ───────────── 1) 좋아요 ───────────── */
    @Transactional
    public void likePost(Long userId, Long postId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자 없음"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));

        // 이미 눌렀다면 예외
        if (postLikeRepository.existsByUserAndPost(user, post)) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
        }

        postLikeRepository.save(new PostLike(user, post));
    }

    /* ───────────── 2) 좋아요 취소 ───────────── */
    @Transactional
    public void unlikePost(Long userId, Long postId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자 없음"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));

        PostLike like = postLikeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new IllegalStateException("눌렀던 좋아요가 없습니다."));

        postLikeRepository.delete(like);
    }

    /* ───────────── 3) 좋아요 개수 ───────────── */
    public long countLikes(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));

        return postLikeRepository.countByPost(post);
    }
}
