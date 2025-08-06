// src/pages/Ranking/Ranking.jsx
import React from 'react';
import './Ranking.css';
import { FaPercent, FaThumbsUp, FaMedal } from 'react-icons/fa';
import MyRankingInfo from './MyRankingInfo';

const Ranking = () => {
  const myId = 4;

  const myStats = {
    rank: 47,
    total: 2847,
    percentile: 1.7,
    winRate: 87,
    likes: 1234,
    gameRecord: '26W - 4L',
    postLikes: 1234,
    change: +12,
    likeDiff: +89,
  };

  const topPlayers = [
    { id: 1, name: '아이유', points: 2847 },
    { id: 2, name: '정혜인', points: 2634 },
    { id: 3, name: '유재석', points: 2421 },
    { id: 4, name: '나', points: 1847 },
  ];

  return (
    <div className="ranking-container">
      {/* 상단 헤더 */}
      <div className="ranking-header-container">
        <div className="ranking-header-left">
          <div className="ranking-rank">#{myStats.rank}</div>
          <div className="ranking-total">전체 {myStats.total.toLocaleString()}명 중</div>
        </div>

        <div className="ranking-header-right">
          <div className="ranking-stat">
            <FaPercent className="stat-icon" />
            <div>
              <div className="stat-value">상위 {myStats.percentile}%</div>
              <div className="stat-label">백분위 순위</div>
            </div>
          </div>
          <div className="ranking-stat">
            <FaMedal className="stat-icon" />
            <div>
              <div className="stat-value">{myStats.winRate}%</div>
              <div className="stat-label">승률</div>
            </div>
          </div>
          <div className="ranking-stat">
            <FaThumbsUp className="stat-icon" />
            <div>
              <div className="stat-value">{myStats.likes.toLocaleString()}</div>
              <div className="stat-label">받은 좋아요 수</div>
            </div>
          </div>
        </div>
      </div>

      {/* 중단: Figma 기반 성과지표 컴포넌트 */}
      <MyRankingInfo
        myInfo={{
          rank: myStats.rank,
          total: myStats.total,
          winRate: myStats.winRate,
          gameRecord: myStats.gameRecord,  // 문자열로 전달
          likesReceived: myStats.postLikes,
          likeDiff: myStats.likeDiff,
          rankChange: myStats.change
        }}
      />

      {/* 하단 Top Players */}
      <div className="ranking-bottom">
        <h4>Top Players</h4>
        {topPlayers.map((player, idx) => {
          const isMe = player.id === myId;
          const medalColor = ['#fff3cd', '#e2e3e5', '#f8d7da'][idx] || (isMe ? '#e9d8fd' : 'white');
          const textColor = isMe ? '#6f42c1' : 'black';
          return (
            <div
              key={player.id}
              className="top-player"
              style={{ backgroundColor: medalColor, color: textColor }}
            >
              <div className="player-rank">#{idx + 1}</div>
              <div className="player-name">{player.name}</div>
              <div className="player-points">{player.points.toLocaleString()} pts</div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default Ranking;
