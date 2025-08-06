// src/pages/MyPage/MyPage.jsx
import React from 'react';
import './MyPage.css';

import AccountInfo from './components/AccountInfo';
import PasswordChangeForm from './components/PasswordChangeForm';
import ActivitySummary from './components/ActivitySummary';
import RankStatus from './components/RankStatus';
import RecentAchievements from './components/RecentAchievements';
import MyPosts from './components/MyPosts';

const MyPage = () => {
  return (
    <div className="mypage-container">
      {/* 🧑‍🎓 상단 프로필 카드 */}
      <div className="profile-card">
        <img
          src="/assets/mypage.jpg"
          alt="프로필"
          className="profile-img"
        />
        <div className="profile-info">
          <h2 className="user-name">김아코</h2>
          <span className="user-status">🟢 승인됨</span>
          <p className="member-since">Member since March 2024</p>
        </div>
      </div>

      {/* 🧱 메인 그리드 (좌/우 카드 영역) */}
      <div className="mypage-grid">
        {/* 왼쪽: 계정정보 + 비밀번호변경 */}
        <div className="left-column">
          <AccountInfo />
          <PasswordChangeForm />
        </div>

        {/* 오른쪽: 활동요약 + 순위 */}
        <div className="right-column">
          <ActivitySummary />
          <RankStatus />
        </div>
      </div>

      {/* 📈 중간 성과 박스 */}
      <RecentAchievements />

      {/* 📝 게시글 내역 */}
      <MyPosts />
    </div>
  );
};

export default MyPage;
