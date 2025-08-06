// src/pages/Board/BoardForm.jsx
import React, { useState } from 'react';

const BoardForm = ({ onAddPost }) => {
  const [category, setCategory] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const handleSubmit = () => {
    if (!category || !title || !content) {
      alert('모든 항목을 입력해주세요.');
      return;
    }

    const newPost = {
      category,
      title,
      content,
    };

    onAddPost(newPost);
    setCategory('');
    setTitle('');
    setContent('');
  };

  return (
    <div style={{ marginBottom: '30px' }}>
      <h3>게시글 작성하기</h3>
      <select
        value={category}
        onChange={e => setCategory(e.target.value)}
        style={{ display: 'block', marginBottom: '10px' }}
      >
        <option value="">카테고리 선택</option>
        <option value="Q&A">Q&A</option>
        <option value="정보">정보</option>
        <option value="이슈">이슈</option>
      </select>
      <input
        type="text"
        placeholder="제목"
        value={title}
        onChange={e => setTitle(e.target.value)}
        style={{ width: '100%', marginBottom: '10px' }}
      />
      <textarea
        placeholder="내용"
        value={content}
        onChange={e => setContent(e.target.value)}
        style={{ width: '100%', height: '100px', marginBottom: '10px' }}
      />
      <button onClick={handleSubmit}>작성</button>
    </div>
  );
};

export default BoardForm;
