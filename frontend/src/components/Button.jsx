import './Button.css';

function Button({ children, onClick, name}) {
  return (
    <button className={`Button ${name}`} onClick={onClick}>
      {children}
    </button>
  );
}

export default Button;
