// src/pages/Home/GameSection.jsx
import React from 'react';
import './Home.css';

const games = [
  {
    id: 1,
    title: '지각이다! 동국대로 뛰어!',
    description: '늦잠 잔 동대생, 수업 시작까지 남은 시간은 단 10분! 과연 캠퍼스를 질주해 지각을 면할 수 있을까?',
    image: '/assets/game1.jpg', // public 폴더 기준 경로
  },
  {
    id: 2,
    title: 'A+은 내꺼야!',
    description: '시험기간, 총알 대신 날아오는 건 스마트폰과 SNS! 방해물을 피해 집중력을 지키고, 과제와 필기구를 모아 A+을 노려보자!',
    image: '/assets/game2.jpg',
  },
];

const GameSection = () => {
  return (
    <section className="game-section">
      <h2>게임들</h2>
      <div className="game-cards">
        {games.map((game) => (
          <div key={game.id} className="game-card">
            <img src={game.image} alt={game.title} className="game-thumbnail" />
            <h3>{game.title}</h3>
            <p>{game.description}</p>
            <button>플레이</button>
          </div>
        ))}
      </div>
    </section>
  );
};

export default GameSection;
