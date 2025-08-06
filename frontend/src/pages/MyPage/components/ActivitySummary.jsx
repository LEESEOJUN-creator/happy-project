import React from "react";
import "./ActivitySummary.css";

import { FaPen, FaHeart, FaCommentDots } from "react-icons/fa";

const ActivitySummary = () => {
  return (
    <div className="activity-card">
      <h3 className="activity-title">내 활동</h3>
      <ul className="activity-list">
        <li className="activity-item">
          <div className="icon-wrapper blue"><FaPen /></div>
          <div className="text-wrapper">
            <span className="label">내 게시물</span>
            <span className="value">12</span>
          </div>
        </li>
        <li className="activity-item">
          <div className="icon-wrapper red"><FaHeart /></div>
          <div className="text-wrapper">
            <span className="label">좋아요 수</span>
            <span className="value">284</span>
          </div>
        </li>
        <li className="activity-item">
          <div className="icon-wrapper green"><FaCommentDots /></div>
          <div className="text-wrapper">
            <span className="label">내 댓글</span>
            <span className="value">67</span>
          </div>
        </li>
      </ul>
    </div>
  );
};

export default ActivitySummary;
