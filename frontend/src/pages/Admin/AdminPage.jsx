import Input from '../../components/Input';
import Button from '../../components/Button';
import TextArea from '../../components/TextArea';
import './AdminPage.css';

const AdminPage = () => {
  return (
    <div className="admin-dashboard">
      <aside className="sidebar">
        <h3>관리 포털</h3>
        <ul>
          <li>Dashboard</li>
          <li>학생명 확인</li>
          <li>명령</li>
          <li>공지사항</li>
          <li>유저툴</li>
          <li>설정</li>
        </ul>
      </aside>
      <main className="main-panel">
        <h2>관리자 페이지</h2>
        <div className="summary-cards">
          <div>보류 승인 인증: 24</div>
          <div>오늘 승인됨: 18</div>
          <div>주간 수익: $2,450</div>
          <div>활동 승인 학생 수: 1,247</div>
        </div>

        <section className="student-check">
          <h3>학생명 확인</h3>
          <div>
            <p>정원영</p>
            <Button>승인</Button>
            <Button>거절</Button>
          </div>
          <div>
            <p>차윤주</p>
            <Button>승인</Button>
            <Button>거절</Button>
          </div>
        </section>

        <section className="ranking">
          <h3>주간 랭킹</h3>
          <ol>
            <li>이예주 - $500</li>
            <li>전채원 - $300</li>
          </ol>
        </section>

        <section className="notice-form">
          <h3>공지사항 작성</h3>
          <Input type="text" placeholder="공지사항 제목" />
          <TextArea placeholder="공지 메시지를 입력해주세요"></TextArea>
          <Button>공지사항 올리기</Button>
          <Button>임시저장</Button>
        </section>
      </main>
    </div>
  );
};

export default AdminPage;
