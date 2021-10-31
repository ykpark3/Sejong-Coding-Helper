import React, { useState } from 'react';
import ReactModal from 'react-modal';
import reactDom from 'react-dom';
import '../../css/modal/RoomAddingModal.css';
import { LOGIN_BEFORE } from '../../redux/login/loginTypes';
import axios from 'axios';

const ChatRoomAddingModal = ({ setModalOn }) => {
  const [title, setTitle] = useState('');
  const [professorName, setProfessorName] = useState('');
  const [stuNums, setStuNums] = useState('');

  const createChatRoom = () => {
    if (title.trim() === '' || professorName.trim() === '' || stuNums.trim() === '') {
        alert('🙄❗❓ 입력칸을 전부 입력해주세요. ❓❗🙄');
        return;
    }
    const reg_stunum = /^[0-9]{8}$/;

    let stuNumArr = stuNums.split('\n');
    for(let element of stuNumArr){

        if(!reg_stunum.test(element)){
            alert('🙄❗❓ 올바르지 않은 학번이 있습니다. 다시 입력해주세요. ❓❗🙄');
            return;
        }
        console.log(element);
    }


  };

  return (
    <div className="roomAddingmodal">
      <div className="bg" />
      <div className="roomAddingmodalBox">
        <p className="modalTitle">채팅방 생성</p>

        <div className="oneRow">
          <p>과목명</p>
          <input
            placeholder="프로그래밍 입문 P"
            onChange={(e) => {
              setTitle(e.target.value);
            }}
          ></input>
        </div>

        <div className="oneRow">
          <p>교수명</p>
          <input
            placeholder="홍길동"
            onChange={(e) => {
              setProfessorName(e.target.value);
            }}
          ></input>
        </div>

        <div className="secondRow">
          <p>학 번</p>
          <textarea
            placeholder="170123123&#13;&#10;180123123&#13;&#10;190123123"
            onChange={(e) => {
              setStuNums(e.target.value);
            }}
          ></textarea>
        </div>
        <p id="textAreaTip">
          ※ 수강생 학번을 입력해주세요. <u>한 학번마다 줄바꿈을 해주세요.</u>
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
                createChatRoom();
            }}
          >
            확 인
          </button>
        </div>
      </div>
    </div>
  );
};

export default ChatRoomAddingModal;
