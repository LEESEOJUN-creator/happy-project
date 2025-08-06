import React from 'react';
import './Home.css';
import { FaTrophy } from 'react-icons/fa';

const RankingPreview = () => {
  const myRank = 127;
  const totalPoints = 2847;
  const winsThisWeek = 18;

  const topPlayers = [
    { id: 1, name: '김우빈', points: '4,892점', img: '/assets/user1.jpg' },
    { id: 2, name: '신세경', points: '4,756점', img: '/assets/user2.jpg' },
    { id: 3, name: '박보검', points: '4,523점', img: '/assets/user3.jpg' },
  ];

  return (
    <section className="ranking-preview">
      <div className="ranking-header">
        <h2>내 순위</h2>
        <FaTrophy className="trophy-icon" />
      </div>

      {/* 랭킹 숫자 강조 박스 */}
      <div className="rank-box">
        <div className="rank-number">#{myRank}</div>
        <div className="rank-label">랭킹</div>
      </div>

      {/* 통계 박스 */}
      <div className="stat-row">
        <div className="stat-box">
          <div className="stat-value">{totalPoints.toLocaleString()}</div>
          <div className="stat-label">총 점수</div>
        </div>
        <div className="stat-box">
          <div className="stat-value">{winsThisWeek}</div>
          <div className="stat-label">이긴 게임 수</div>
        </div>
      </div>

      {/* Top Players 목록 */}
      <div className="top-players">
        <h3>Top Players</h3>
        {topPlayers.map((player, index) => (
          <div key={player.id} className="player-row">
            <div className={`rank-medal rank-${index + 1}`}>{index + 1}</div>
            <img src={player.img} alt={player.name} className="player-img" />
            <div className="player-info">
              <div className="player-name">{player.name}</div>
              <div className="player-points">{player.points}</div>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
};

export default RankingPreview;
