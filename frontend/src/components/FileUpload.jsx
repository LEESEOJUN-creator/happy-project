const FileUpload = ({ label, accept, onChange }) => (
  <div className="file-upload">
    <label>
      {label}
      <input type="file" accept={accept} onChange={onChange} />
    </label>
  </div>
);
export default FileUpload;
