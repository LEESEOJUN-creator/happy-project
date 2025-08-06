import React from "react";
import "./AccountInfo.css";

const AccountInfo = () => {
  return (
    <div className="account-card">
      <div className="account-header">
        <h3>계정 정보</h3>
        <button className="edit-button">수정</button>
      </div>
      <div className="account-body">
        <div className="info-row">
          <span className="info-label">학년</span>
          <span className="info-value">3학년</span>
        </div>
        <div className="info-row">
          <span className="info-label">Email</span>
          <span className="info-value">kimdonguk@gmail.com</span>
        </div>
        <div className="info-row">
          <span className="info-label">계정 상태</span>
          <span className="info-value">
            <span className="status-dot" /> Approved & Active
          </span>
        </div>
      </div>
    </div>
  );
};

export default AccountInfo;
