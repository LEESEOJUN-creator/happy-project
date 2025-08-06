const Avatar = ({ src, alt, size = 40 }) => (
  <img src={src} alt={alt} width={size} height={size} style={{ borderRadius: '50%' }} />
);
export default Avatar;