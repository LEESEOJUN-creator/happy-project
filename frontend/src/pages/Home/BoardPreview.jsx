// src/pages/Home/BoardPreview.jsx
import React from 'react';
import './Home.css';
import { FaHeart, FaCommentDots } from 'react-icons/fa';

const posts = [
  {
    id: 1,
    author: '아이유',
    time: '2시간 전',
    content: '이번 주에는 포인터를 집중적으로 공부했어요! 초보자 시절 가장 헷갈렸던 int *p = &a; 같은 개념을 예제로 다시 정리해봤어요.',
    likes: 24,
    comments: 3,
  },
  {
    id: 2,
    author: '정해인',
    time: '4시간 전',
    content: 'INNER JOIN, LEFT JOIN 차이를 계속 헷갈려서 직접 만든 학생-강의-수강내역 예제로 정리했습니다. SELECT * FROM student s JOIN enroll e ON ...',
    likes: 42,
    comments: 7,
  },
];

const BoardPreview = () => {
  return (
    <section className="board-preview">
      <div className="section-header">
        <h2>게시판</h2>
        <a href="/board" className="view-all">모두보기</a>
      </div>
      {posts.map((post) => (
        <div key={post.id} className="post-card">
          <div className="post-header">
            <span className="post-author">{post.author}</span>
            <span className="post-meta">· {post.time}</span>
          </div>
          <p className="post-content">{post.content}</p>
          <div className="post-stats">
            <span><FaHeart style={{ color: '#f87171' }} /> {post.likes}</span>
            <span><FaCommentDots style={{ color: '#60a5fa' }} /> {post.comments}</span>
          </div>
        </div>
      ))}
    </section>
  );
};

export default BoardPreview;
