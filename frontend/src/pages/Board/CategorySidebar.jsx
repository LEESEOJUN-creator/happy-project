// src/pages/Board/CategorySidebar.jsx
import React from 'react';

const categories = ['전체', '인기많은', 'Q&A', '정보', '이슈'];

const CategorySidebar = ({ selected, onSelect }) => {
  return (
    <aside style={{ width: '200px' }}>
      <h3>카테고리</h3>
      <ul style={{ listStyle: 'none', padding: 0 }}>
        {categories.map(cat => (
          <li
            key={cat}
            onClick={() => onSelect(cat)}
            style={{
              cursor: 'pointer',
              fontWeight: selected === cat ? 'bold' : 'normal',
              marginBottom: '10px',
            }}
          >
            {cat}
          </li>
        ))}
      </ul>
    </aside>
  );
};

export default CategorySidebar;
