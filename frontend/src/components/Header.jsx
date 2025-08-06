// src/components/Header.jsx
import React from 'react';
import { Link, useLocation } from 'react-router-dom';

const Header = ({ isLoggedIn, onLogout }) => {
  const location = useLocation();
  const currentPath = location.pathname;

  const isActive = (path) => currentPath === path;

  return (
    <header style={styles.header}>
      <nav style={styles.nav}>
        <div style={styles.left}>
          <Link to="/" style={isActive('/') ? styles.activeLink : styles.inactiveLink}>
            <span role="img" aria-label="home">🏠</span> 홈
          </Link>
          <Link to="/ranking" style={isActive('/ranking') ? styles.activeLink : styles.inactiveLink}>
            <span role="img" aria-label="ranking">🏆</span> 랭킹
          </Link>
          <Link to="/board" style={isActive('/board') ? styles.activeLink : styles.inactiveLink}>
            <span role="img" aria-label="board">💬</span> 게시판
          </Link>
        </div>
        <div style={styles.right}>
          {isLoggedIn ? (
            <>
              <Link to="/mypage" style={styles.user}>김아코</Link>
              <button onClick={onLogout} style={styles.logout}>로그아웃</button>
            </>
          ) : (
            <>
              <Link to="/login" style={styles.loginBtn}>로그인</Link>
              <Link to="/signup" style={styles.signupBtn}>회원가입</Link>
            </>
          )}
        </div>
      </nav>
    </header>
  );
};

const baseLinkStyle = {
  textDecoration: 'none',
  fontWeight: 'bold',
  fontSize: '16px',
  display: 'flex',
  alignItems: 'center',
  gap: '5px',
};

const styles = {
  header: {
    padding: '12px 24px',
    backgroundColor: '#e0e7ff',
    borderBottom: '1px solid #ccc',
  },
  nav: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  left: {
    display: 'flex',
    alignItems: 'center',
    gap: '150px', // 메뉴 간 간격 넉넉히
  },
  right: {
    display: 'flex',
    alignItems: 'center',
    gap: '20px', // 버튼 간격 약간 넓힘
  },
  activeLink: {
    ...baseLinkStyle,
    color: '#000',
  },
  inactiveLink: {
    ...baseLinkStyle,
    color: '#a0a0a0',
  },
  user: {
    textDecoration: 'none',
    color: '#444',
    fontWeight: 'bold',
    backgroundColor: '#fff',
    borderRadius: '20px',
    padding: '5px 10px',
  },
  logout: {
    background: 'none',
    border: 'none',
    color: 'red',
    cursor: 'pointer',
    fontWeight: 'bold',
  },
  loginBtn: {
    textDecoration: 'none',
    backgroundColor: '#2563eb',
    color: 'white',
    borderRadius: '6px',
    padding: '6px 12px',
    fontWeight: 'bold',
  },
  signupBtn: {
    textDecoration: 'none',
    border: '2px solid #2563eb',
    color: '#2563eb',
    borderRadius: '6px',
    padding: '6px 12px',
    fontWeight: 'bold',
    backgroundColor: 'white',
  },
};

export default Header;
