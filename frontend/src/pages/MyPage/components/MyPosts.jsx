// src/pages/MyPage/components/MyPosts.jsx
import React, { useState } from 'react';
import './MyPosts.css'; // 선택 스타일

const MyPosts = () => {
  const [activeTab, setActiveTab] = useState('posts');

  const posts = [
    {
      title: 'The Future of AI in Creative Writing',
      content: 'Exploring how artificial intelligence is transforming the landscape of creative writing...',
      date: '2 days ago',
      likes: 42,
      comments: 5,
    },
    {
      title: 'Minimalism in Modern Design',
      content: 'A deep dive into the principles of minimalist design and its impact on user experience...',
      date: '1 week ago',
      likes: 26,
      comments: 15,
    },
  ];

  const tabs = {
    posts: '작성글',
    comments: '댓글',
    likes: '좋아요 기록',
  };

  return (
    <div className="my-posts">
      <div className="tabs">
        {Object.entries(tabs).map(([key, label]) => (
          <button
            key={key}
            className={activeTab === key ? 'active' : ''}
            onClick={() => setActiveTab(key)}
          >
            {label}
          </button>
        ))}
      </div>

      <div className="post-list">
        {activeTab === 'posts' &&
          posts.map((post, index) => (
            <div key={index} className="post-item">
              <div className="post-header">
                <h4>{post.title}</h4>
                <span className="post-date">{post.date}</span>
              </div>
              <p className="post-content">{post.content}</p>
              <div className="post-meta">
                ❤️ {post.likes} &nbsp;&nbsp; 💬 {post.comments}
              </div>
            </div>
          ))}

        {activeTab !== 'posts' && (
          <p className="empty-msg">아직 {tabs[activeTab]}이 없습니다.</p>
        )}
      </div>
    </div>
  );
};

export default MyPosts;
