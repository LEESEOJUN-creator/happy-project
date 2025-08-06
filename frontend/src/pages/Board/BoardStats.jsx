// src/pages/Board/BoardStats.jsx
import React from 'react';

const BoardStats = ({ posts }) => {
  const totalPosts = posts.length;
  const todayPosts = posts.filter(post => post.time === '방금 전').length;
  const userCount = 89; // 임시

  return (
    <div
      style={{
        backgroundColor: '#f6f8fa',
        borderRadius: '10px',
        padding: '15px',
        marginTop: '20px',
        width: '180px',
      }}
    >
      <h4>게시판 현황</h4>
      <ul style={{ listStyle: 'none', padding: 0, margin: 0, fontSize: '14px' }}>
        <li>전체 게시글: <strong>{totalPosts}</strong></li>
        <li>사용자 수: <strong>{userCount}</strong></li>
        <li>오늘 게시글: <strong>{todayPosts}</strong></li>
      </ul>
    </div>
  );
};

export default BoardStats;
