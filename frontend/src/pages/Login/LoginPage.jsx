import React from "react";
import { Link } from "react-router-dom";
import Input from "../../components/Input";
import Button from "../../components/Button";
import "./LoginPage.css";



function LoginPage(){
  return (
    <div className="login-page-wrapper">
      <div className="login-container">
        <h2>환영합니다</h2>
        <p>당신의 계정으로 로그인해주세요</p>

        <form className="login-form">
          <label htmlFor="email">이메일</label>
          <Input id="email" type="email" placeholder="이메일을 입력해주세요" />

          <label htmlFor="password">비밀번호</label>
          <Input id="password" type="password" placeholder="비밀번호를 입력해주세요" />

          <div className="link-container">
            <label>
              <Input type="checkbox" />계정 기억하기
              </label>
            <a href="#">아이디/비밀번호 찾기</a>
          </div>

          <Button type="submit" name="login-button">
            <span className="lock-icon">
              {/* 자물쇠 SVG */}
              <svg xmlns="http://www.w3.org/2000/svg" width="18" height="21" viewBox="0 0 18 21" fill="none">
                <g clipPath="url(#clip0)">
                  <path
                    d="M5.875 6.125V8H12.125V6.125C12.125 4.39844 10.7266 3 9 3C7.27344 3 5.875 4.39844 5.875 6.125ZM3.375 8V6.125C3.375 3.01953 5.89453 0.5 9 0.5C12.1055 0.5 14.625 3.01953 14.625 6.125V8H15.25C16.6289 8 17.75 9.12109 17.75 10.5V18C17.75 19.3789 16.6289 20.5 15.25 20.5H2.75C1.37109 20.5 0.25 19.3789 0.25 18V10.5C0.25 9.12109 1.37109 8 2.75 8H3.375Z"
                    fill="white"
                  />
                </g>
                <defs>
                  <clipPath id="clip0">
                    <path d="M0.25 0.5H17.75V20.5H0.25V0.5Z" fill="white" />
                  </clipPath>
                </defs>
              </svg>
            </span>
            로그인
          </Button>

          <div className="signup-link">
            아직 계정이 없으신가요?<Link to="/Signup">회원가입</Link>

          </div>

          <div className="social-buttons">
            <Button name="Google">Google</Button>
            <Button name="Facebook">Facebook</Button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
