// src/pages/Ranking/TopPlayers.jsx
import React from 'react';

const TopPlayers = ({ topPlayers, myId }) => {
  const getMedalColor = (rank) => {
    switch (rank) {
      case 1: return '#fff3cd'; // 금색
      case 2: return '#e2e3e5'; // 은색
      case 3: return '#f8d7da'; // 동색
      default: return 'transparent';
    }
  };

  return (
    <div style={{ background: 'white', padding: '20px', borderRadius: '12px' }}>
      <div style={{ fontSize: '18px', fontWeight: 'bold', marginBottom: '12px' }}>Top Players</div>
      {topPlayers.map((player, idx) => {
        const isMine = player.id === myId;
        return (
          <div
            key={player.id}
            style={{
              background: isMine ? '#e9d8fd' : getMedalColor(idx + 1),
              borderRadius: '8px',
              padding: '12px',
              marginBottom: '8px',
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'center',
              fontWeight: isMine ? 'bold' : 'normal',
              color: isMine ? '#6f42c1' : 'black'
            }}
          >
            <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
              <span style={{ width: '24px', textAlign: 'center' }}>{idx + 1}</span>
              <span>{player.name}</span>
            </div>
            <div>{player.points.toLocaleString()} pts</div>
          </div>
        );
      })}
    </div>
  );
};

export default TopPlayers;
