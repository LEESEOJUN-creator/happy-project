// src/pages/Board/BoardList.jsx
import React from 'react';
import PostCard from './PostCard';

const BoardList = ({ posts, category, onLike }) => {
  const filtered = posts.filter(post => {
    if (category === '전체') return true;
    if (category === '인기많은') return true;
    return post.category === category;
  });

  const sorted = category === '인기많은'
    ? [...filtered].sort((a, b) => b.likes - a.likes)
    : filtered;

  return (
    <div>
      {sorted.map(post => (
        <PostCard key={post.id} post={post} onLike={onLike} />
      ))}
    </div>
  );
};

export default BoardList;
