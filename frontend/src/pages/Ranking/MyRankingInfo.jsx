// src/pages/Ranking/MyRankingInfo.jsx
import React from 'react';
import { FaGamepad, FaHeart } from 'react-icons/fa';
import './Ranking.css';

const MyRankingInfo = ({ myInfo }) => {
  const {
    rank,
    total,
    winRate,
    gameRecord,
    likesReceived,
    likeDiff,
    rankChange,
  } = myInfo;

  const percentile = ((1 - rank / total) * 100).toFixed(1); // 상위 %

  return (
    <div className="ranking-middle">
      {/* ✅ 내 위치 카드 */}
      <div className="ranking-card">
        <h4 className="card-title">내 위치</h4>
        <div className="rank-line">
          현재 랭킹 <strong>#{rank}</strong>
        </div>
        <div className="progress-bar">
          <div
            className="progress"
            style={{ width: `${percentile}%` }}
          />
        </div>
        <div className="rank-change">⬆ 이번주 {rankChange} 계단 상승!</div>
      </div>

      {/* ✅ 성과지표 카드 */}
      <div className="ranking-card">
        <h4 className="card-title">성과지표</h4>

        {/* 게임 전적 */}
        <div className="indicator-row">
          <div className="indicator-left">
            <div className="indicator-icon blue"><FaGamepad /></div>
            <div className="indicator-text">
              <div className="label-bold">게임 전적</div>
              <div className="label-sub">승 / 패 비율</div>
            </div>
          </div>
          <div className="indicator-right">
            <div className="value-bold">{gameRecord}</div>
            <div className="value-sub green">승률 {winRate}%</div>
          </div>
        </div>

        {/* 게시글 좋아요 수 */}
        <div className="indicator-row">
          <div className="indicator-left">
            <div className="indicator-icon purple"><FaHeart /></div>
            <div className="indicator-text">
              <div className="label-bold">게시글 좋아요 수</div>
              <div className="label-sub">커뮤니티 반응</div>
            </div>
          </div>
          <div className="indicator-right">
            <div className="value-bold">{likesReceived.toLocaleString()}</div>
            <div className="value-sub purple">이번 주 +{likeDiff} 증가</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyRankingInfo;
