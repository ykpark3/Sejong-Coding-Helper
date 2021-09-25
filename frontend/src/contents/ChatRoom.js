import React, { useEffect, useRef, useSelector } from 'react';
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import { Link } from 'react-router-dom';
import { connect, useDispatch } from 'react-redux';
import {
  addMsgData,
  getBotResponse,
  fetchChatData,
} from '../redux/chat/chatActions';
import '../css/Chatroom.css';

const chatData = ({ chatsData }) => {
  // useEffect(()=>{
  //   fetChatData()
  // }, [])

  const chatItems = chatsData.map((chat) => {
    if (chat.sender === 'bot') {
      return <BotChatMsgItem msg={chat.msg} key={chat.id} />;
    } else if (chat.sender === 'user') {
      return <UserChatMsgItem msg={chat.msg} key={chat.id} />;
    }
  });

  return <>{chatItems}</>;
};

function BotChatMsgItem({ msg }) {
  return (
    <li className="botMsg">
      <img src="img/logo.png"/>
      <div>
        <p>세종 코딩 헬퍼</p>
        <p>{msg}</p>
      </div>
    </li>
  );
}

function UserChatMsgItem({ msg }) {
  return (
    <li className="userMsg">
      <div>
        <p>나</p>
        <p>{msg}</p>
      </div>
    </li>
  );
}

const ChatRoom = ({ num, chatsData, addMsgData, getBotResponse }) => {
  const msgInput = useRef();
  const scrollRef = useRef();

  useEffect(() => {
    scrollToBottom();
  });

  const scrollToBottom = () => {
    console.log('h');
    scrollRef.current.scrollIntoView({ behavior: 'smooth' });
  };

  const handleKeyPress = (e) =>{
    if (e.key === "Enter") {
      sendMsg();
    }
  }

  function sendMsg() {
    const text = msgInput.current.value;

    if (text === '') {
      return;
    }

    addMsgData(num, 'user', text);
    msgInput.current.value = '';

    getBotResponse(text);
    //scrollToBottom();
  }

  return (
    <div style={{ width: '100%' }}>
      <VerticalHeader/>
      <HorizontalHeader/>

      <div id="chatRoomBody">
        <div id="emptySpace1" />

        <div id="secondHorizontalNav">
          <h3>실시간 키워드 정보</h3>
          <div>
            <li>
              <p>C언어 변수</p>
              <p>기본적인 변수 선언, 변수 할당</p>
            </li>
            <li>
              <p>관계 연산자</p>
              <p>관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.</p>
            </li>
            <li>
              <p>조건문의 종류</p>
              <p>조건식에 따라 실행하는 명령문의 흐름 제어문</p>
            </li>
            <li>
              <p>오류 코드 printf</p>
              <p>주로 C코드 출력문에서의 문법 오류</p>
            </li>
          </div>
        </div>

        <div id="mainChatting">
          <h3>대화하기</h3>

          <div id="chattingSpace">
            {chatData({ chatsData })}
            <div ref={scrollRef}></div>
          </div>

          <div id="inputForm">
            <input id="msgInput" ref={msgInput} onKeyPress={handleKeyPress}></input>
            <button onClick={() => sendMsg()}>전 송</button>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ chats }) => {
  console.log(chats.items);

  return {
    chatsData: chats.items,
    num: chats.num,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    fetchChatData: () => dispatch(fetchChatData()),
    addMsgData: (id, sender, msg) => dispatch(addMsgData(id, sender, msg)),
    getBotResponse: (msg) => dispatch(getBotResponse(msg)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(ChatRoom);
