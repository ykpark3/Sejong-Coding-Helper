import React, { useState } from 'react';
import ReactModal from 'react-modal';
import reactDom from 'react-dom';
import '../../css/modal/RoomAddingModal.css';
import { LOGIN_BEFORE } from '../../redux/login/loginTypes';
import axios from 'axios';

const WithdrawalModal = ({ setModalOn }) => {
  const [nowPw, setNowPw] = useState('');
  const [newPw, setNewPw] = useState('');
  const [newPwCheck, setNewPwCheck] = useState('');

  const withdrawMember = () => {
    if (nowPw === '' || newPw === '' || newPwCheck === '') {
      alert('🙄❗❓ 입력칸을 전부 입력해주세요. ❓❗🙄');
      return;
    }
  };

  return (
    <div className="roomAddingmodal">
      <div className="bg" />
      <div className="roomAddingmodalBox">
        <p className="modalTitle">회원 탈퇴</p>

        <div className="oneRow">
          <p style={{ width: '130px', marginTop: '12px' }}>비밀번호</p>
          <input
            style={{ width: '230px', fontFamily: 'none' }}
            type="password"
            onChange={(e) => {
              setNowPw(e.target.value);
            }}
          ></input>
        </div>

        <div className="oneRow">
          <p style={{ width: '130px', marginTop: '12px' }}>비밀번호 확인</p>
          <input
            style={{ width: '230px', fontFamily: 'none' }}
            type="password"
            onChange={(e) => {
              setNewPw(e.target.value);
            }}
          ></input>
        </div>

        <div className="oneRow">
          <p style={{ width: '130px', marginTop: '12px',marginBottom:'20px' }}>삭제 문구 입력</p>
          <input
            style={{ width: '230px',marginBottom:'20px' }}
            placeholder="회원 탈퇴"
            onChange={(e) => {
              setNewPwCheck(e.target.value);
            }}
          ></input>
        </div>
        <p id="textAreaTip" style={{ margin:'0px 0px 20px 0px' }}>
          ※ 정말로 회원 탈퇴를 원하시면 '회원 탈퇴'를 입력해주세요.
        </p>

        <div className="cuttingLine"></div>
        <div className="bntGroup">
          <button
            style={{ color: '#ff7777' }}
            onClick={() => {
              setModalOn(false);
            }}
          >
            취 소
          </button>
          <button
            onClick={() => {
              withdrawMember();
            }}
          >
            확 인
          </button>
        </div>
      </div>
    </div>
  );
};

export default WithdrawalModal;
