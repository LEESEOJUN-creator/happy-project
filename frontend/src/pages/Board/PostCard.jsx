// src/pages/Board/PostCard.jsx
import React from 'react';

const PostCard = ({ post, onLike }) => {
  const handleLike = () => {
    onLike(post.id);
  };

  return (
    <div
      style={{
        border: '1px solid #ddd',
        padding: '15px',
        borderRadius: '8px',
        marginBottom: '15px',
        backgroundColor: '#fff',
      }}
    >
      <div style={{ marginBottom: '8px', fontSize: '14px', color: '#666' }}>
        <strong>{post.author}</strong> · {post.time} · <span>{post.category}</span>
      </div>
      <h4>{post.title}</h4>
      <p>{post.content}</p>
      <div style={{ fontSize: '12px', marginTop: '10px' }}>
        <button
          onClick={handleLike}
          style={{
            background: 'none',
            border: 'none',
            color: 'red',
            cursor: 'pointer',
            fontSize: '14px',
            marginRight: '10px',
          }}
        >
          ❤️ 좋아요 {post.likes}
        </button>
        💬 {post.comments} · 🔄 {post.shares}
      </div>
    </div>
  );
};

export default PostCard;
