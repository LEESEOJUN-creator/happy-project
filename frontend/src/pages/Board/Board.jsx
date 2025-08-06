// src/pages/Board/Board.jsx
import React, { useState } from 'react';
import './Board.css';

import BoardForm from './BoardForm';
import BoardList from './BoardList';
import CategorySidebar from './CategorySidebar';
import BoardStats from './BoardStats';

const Board = () => {
  const [selectedCategory, setSelectedCategory] = useState('전체');

  const [posts, setPosts] = useState([
    {
      id: 1,
      author: '익명의 사용자',
      time: '2시간 전',
      category: 'Q&A',
      title: '질문: 포인터 더블참조 개념이 너무 헷갈려요',
      content: 'C언어에서 int **pp 는... 잘 안잡혀요. 설명해주실 분 있나요?',
      likes: 12,
      comments: 8,
      shares: 3,
    },
    {
      id: 2,
      author: '익명의 사용자',
      time: '5시간 전',
      category: '정보',
      title: '[정보] SQL JOIN 예제 모음 공유합니다',
      content: 'INNER / LEFT / RIGHT JOIN 차이 예제를 정리했어요!',
      likes: 24,
      comments: 15,
      shares: 6,
    },
  ]);

  const handleAddPost = (newPost) => {
    setPosts(prev => [
      {
        ...newPost,
        id: prev.length + 1,
        author: '익명의 사용자',
        time: '방금 전',
        likes: 0,
        comments: 0,
        shares: 0,
      },
      ...prev,
    ]);
  };

  const handleLikePost = (postId) => {
    setPosts(prev =>
      prev.map(post =>
        post.id === postId ? { ...post, likes: post.likes + 1 } : post
      )
    );
  };

  return (
    <div className="board-container" style={{ display: 'flex', padding: '20px' }}>
      <div>
        <CategorySidebar
          selected={selectedCategory}
          onSelect={setSelectedCategory}
        />
        <BoardStats posts={posts} />
      </div>
      <div className="board-main" style={{ flex: 1, marginLeft: '20px' }}>
        <BoardForm onAddPost={handleAddPost} />
        <BoardList
          posts={posts}
          category={selectedCategory}
          onLike={handleLikePost}
        />
      </div>
    </div>
  );
};

export default Board;
