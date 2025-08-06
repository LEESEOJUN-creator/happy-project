import Avatar from "src\components\Avatar.jsx";
const RankingItem = ({ rank, name, points, reward, imgSrc }) => (
  <div className="ranking-item">
    <span>{rank}</span>
    <Avatar src={imgSrc} alt={name} />
    <span>{name}</span>
    <span>{points} points</span>
    <span>{reward}</span>
  </div>
);
export default RankingItem;
