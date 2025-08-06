// src/pages/MyPage/components/RecentAchievements.jsx
import React from 'react';
import './RecentAchievements.css'; // 선택사항

const RecentAchievements = () => {
  const achievements = [
    { icon: '🏆', text: '이번 주 인기 게시물' },
    { icon: '💬', text: '좋아요 100개 달성', sub: '1등' },
  ];

  return (
    <div className="recent-achievements">
      <h3>최근 성과</h3>
      <ul className="achievement-list">
        {achievements.map((item, idx) => (
          <li key={idx} className="achievement-item">
            <span className="icon">{item.icon}</span>
            <div className="text-box">
              <div className="main-text">{item.text}</div>
              {item.sub && <div className="sub-text">{item.sub}</div>}
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RecentAchievements;
