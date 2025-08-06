const Input = ({ label, type = "text", placeholder, ...props }) => (
  <div className="input-group">
    {label && <label>{label}</label>}
    <input type={type} placeholder={placeholder} {...props} />
  </div>
);
export default Input;