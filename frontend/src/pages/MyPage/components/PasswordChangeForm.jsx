import React, { useState } from "react";
import "./PasswordChangeForm.css"; // 🔥 새 스타일 분리

const PasswordChangeForm = () => {
  const [currentPw, setCurrentPw] = useState("");
  const [newPw, setNewPw] = useState("");
  const [confirmPw, setConfirmPw] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (newPw !== confirmPw) {
      alert("새 비밀번호가 일치하지 않습니다.");
      return;
    }
    alert("비밀번호 변경 요청이 전송되었습니다.");
  };

  return (
    <div className="password-card">
      <h3 className="section-title">비밀번호 변경하기</h3>
      <form onSubmit={handleSubmit} className="password-form">
        <input
          type="password"
          placeholder="현재 비밀번호"
          value={currentPw}
          onChange={(e) => setCurrentPw(e.target.value)}
        />
        <input
          type="password"
          placeholder="새 비밀번호"
          value={newPw}
          onChange={(e) => setNewPw(e.target.value)}
        />
        <input
          type="password"
          placeholder="새 비밀번호 확인"
          value={confirmPw}
          onChange={(e) => setConfirmPw(e.target.value)}
        />
        <button type="submit" className="save-btn">비밀번호 변경</button>
      </form>
    </div>
  );
};

export default PasswordChangeForm;
