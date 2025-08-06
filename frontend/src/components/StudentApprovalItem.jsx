import Avatar from "./Avatar";
const StudentApprovalItem = ({ name, info, onApprove, onReject, imgSrc }) => (
  <div className="approval-item">
    <Avatar src={imgSrc} alt={name} />
    <div>
      <div>{name}</div>
      <div>{info}</div>
    </div>
    <button onClick={onApprove}>승인</button>
    <button onClick={onReject}>거절</button>
  </div>
);
export default StudentApprovalItem;
