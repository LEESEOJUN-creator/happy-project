// src/pages/Home/Home.jsx
import React from 'react';
import './Home.css';

import NoticeSection from './NoticeSection';
import GameSection from './GameSection';
import BoardPreview from './BoardPreview';
import RankingPreview from './RankingPreview';

const Home = () => {
  return (
    <div className="home-container">
      <NoticeSection />
      <GameSection />
      <div className="bottom-section">
        <BoardPreview />
        <RankingPreview />
      </div>
    </div>
  );
};

export default Home;
