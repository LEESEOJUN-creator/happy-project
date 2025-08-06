import React from "react";
import "./RankStatus.css"; 

const RankStatus = () => {
  return (
    <div className="rank-card">
      <h3 className="section-title">내 순위</h3>

      <div className="rank-circle">#15</div>
      <p className="rank-desc">Top 작성자<br />이번 달</p>

      <div className="score-bar-group">
        <div className="score-label">
          <span>참여 점수</span>
          <span className="score-value">8.7/10</span>
        </div>
        <div className="score-bar">
          <div className="score-fill" style={{ width: "87%" }}></div>
        </div>
      </div>

      <div className="score-bar-group">
        <div className="score-label">
          <span>게임 점수</span>
          <span className="score-value">9.2/10</span>
        </div>
        <div className="score-bar">
          <div className="score-fill" style={{ width: "92%" }}></div>
        </div>
      </div>
    </div>
  );
};

export default RankStatus;
