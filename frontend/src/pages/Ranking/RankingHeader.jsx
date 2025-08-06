// src/pages/Ranking/RankingHeader.jsx
import React from 'react';
import './Ranking.css';
import { FaHeart, FaPercent, FaMedal } from 'react-icons/fa';
import { RiPercentFill } from 'react-icons/ri';
import { PiTargetBold } from 'react-icons/pi';

const RankingHeader = () => {
  const myRank = 47;
  const totalUsers = 2847;
  const percentile = 1.7;
  const winRate = 87;
  const likesReceived = 1234;

  return (
    <div className="ranking-header-container">
      <div className="ranking-header-left">
        <div className="ranking-rank">#{myRank}</div>
        <div className="ranking-total">전체 {totalUsers.toLocaleString()}명 중</div>
      </div>

      <div className="ranking-header-right">
        <div className="ranking-stat">
          <RiPercentFill className="stat-icon" />
          <div>
            <div className="stat-value">상위 {percentile}%</div>
            <div className="stat-label">백분위 순위</div>
          </div>
        </div>
        <div className="ranking-stat">
          <PiTargetBold className="stat-icon" />
          <div>
            <div className="stat-value">{winRate}%</div>
            <div className="stat-label">승률</div>
          </div>
        </div>
        <div className="ranking-stat">
          <FaHeart className="stat-icon" />
          <div>
            <div className="stat-value">{likesReceived.toLocaleString()}</div>
            <div className="stat-label">받은 좋아요 수</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RankingHeader;
