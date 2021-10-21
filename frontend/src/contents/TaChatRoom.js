import React, { useState, useEffect, useRef, useSelector } from 'react';
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import { Link } from 'react-router-dom';
import { connect, useDispatch } from 'react-redux';
import axios from 'axios';
import { API_BASE_URL } from './utils/Constant';
import {
  addMsgData,
  addRoomData,
  getBotResponse,
  fetchChatData,
} from '../redux/chat/ta_chat/taChatActions';
import {
  changeUserId,
  changeUserName,
  changeType,
  onLoginSuccess,
} from '../redux/login/loginActions';
import '../css/Chatroom.css';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { LOGIN_ORIGIN, LOGIN_BEFORE } from '../redux/login/loginTypes';
import { changeLoadingState } from '../redux/view/viewActions';
import { root2 } from './Root2';
import Root from './Root';

function BotChatMsgItem({ msg, name }) {
  return (
    <li className="botMsg">
      <img src="img/taman.png" />
      <div>
        <p>{name}</p>
        <p>{msg}</p>
      </div>
    </li>
  );
}

function UserChatMsgItem({ msg, name }) {
  return (
    <li className="userMsg">
      <div>
        <p>나</p>
        <p>{msg}</p>
      </div>
    </li>
  );
}

var getCookie = function (name) {
  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value ? value[2] : null;
};

const sockJS = new SockJS(API_BASE_URL + '/websocket');
let stomp = Stomp.over(sockJS);

const TaChatRoom = ({
  loginState,
  userId,
  userName,
  num,
  roomNum,
  chatsData,
  list,
  addMsgData,
  addRoomData,
  history,
  changeUserId,
  changeUserName,

  onLoginSuccess,
  changeType,
  changeLoadingState,
}) => {
  const msgInput = useRef();
  const scrollRef = useRef();
  const [isTa, setTa] = useState(false);
  let studentNumber = getCookie('id');

  useEffect(() => {

    // 동기로 리프래쉬토큰 검증.
    const auth = async () => {
      const result = await root2(onLoginSuccess,changeType,changeLoadingState);
      //console.log(result + ' three');
      if (result === 'success') {
        getIsTa();
      }
    };

    auth();

  }, []);

  const chatData = () => {
    const chatItems = chatsData.map((chat) => {
      let tempName = chat.name;
      if (!isTa) {
        tempName = 'TA ' + chat.name;
      }

      if (chat.userId === userId) {
        return <UserChatMsgItem msg={chat.msg} key={chat.id} />;
      } else {
        return <BotChatMsgItem msg={chat.msg} name={tempName} key={chat.id} />;
      }
    });

    return <>{chatItems}</>;
  };

  const listData = () => {
    const listItems = list.map((item) => {
      let st = 'nonSelectedRoomLi';

      if (
        window.sessionStorage.getItem('roomId') ===
        String(list[item.id - 1].roomId)
      ) {
        st = 'selectedRoomLi';
      }
      return (
        <li
          className={st}
          key={item.id}
          onClick={() => {
            window.sessionStorage.setItem('roomId', list[item.id - 1].roomId);
            window.location.replace('/tachatroom');
          }}
        >
          <p>{item.title}</p>
          <p>{item.des}</p>
        </li>
      );
    });

    return <>{listItems}</>;
  };

  const getIsTa = () => {
    axios
      .post(
        API_BASE_URL + '/user/assistant/' + studentNumber,
        {},
        {
          headers: {
            'Content-type': 'application/json',
            Accept: 'application/json',
          },
          withCredentials: true,
        },
      )
      .then((res) => {
        changeUserId(res.data.id);
        changeUserName(res.data.name);

        if (res.data.isAssistant === '1') {
          setTa(true);
        }

        getChatRoomList(res.data.isAssistant);
      })
      .catch((res) => {
        console.log(res);
        alert('일시적 오류가 발생했습니다. 다시 시도해주세요.');
      });
  };

  const getChatRoomList = (isTa) => {
    axios
      .post(
        API_BASE_URL + '/room/studentId/' + studentNumber,
        {},
        {
          headers: {
            'Content-type': 'application/json',
            Accept: 'application/json',
          },
          withCredentials: true,
        },
      )
      .then((res) => {
        // 채팅방이 없을 경우.
        if (res.data.length === 0) {
          return;
        }

        let firstRoomId = res.data[0].id;

        if (window.sessionStorage.getItem('roomId') === null) {
          window.sessionStorage.setItem('roomId', firstRoomId);
        }

        for (let i = 0; i < res.data.length; i++) {
          let otherName;
          if (String(res.data[i].user.studentNumber) === studentNumber) {
            otherName = res.data[i].user2.name;
          } else {
            otherName = res.data[i].user.name;
          }

          // 접속 계정이 TA일 경우와 아닌 경우 다른 UI를 위해.
          let name;
          if (isTa === '1') {
            name = otherName + ' 학생';
          } else {
            name = 'TA ' + otherName;
          }

          addRoomData(roomNum, res.data[i].id, res.data[i].title, name);
        }
        // 우선 첫번째 채팅방의 채팅 내역 불러오기.
        getChatList(window.sessionStorage.getItem('roomId'), studentNumber);
        connectStomp(window.sessionStorage.getItem('roomId'));
      })
      .catch((res) => {
        console.log(res);
        alert('일시적 오류가 발생했습니다. 다시 시도해주세요.');
      });
  };

  const getChatList = (roomId) => {
    axios
      .post(
        API_BASE_URL + '/chat/roomId/' + roomId,
        {},
        {
          headers: {
            'Content-type': 'application/json',
            Accept: 'application/json',
          },
          withCredentials: true,
        },
      )
      .then((res) => {
        for (let i = 0; i < res.data.length; i++) {
          addMsgData(
            num,
            res.data[i].user.name,
            res.data[i].user.id,
            res.data[i].message,
          );
        }
        scrollToBottom();
      })
      .catch((res) => {
        console.log(res);
        alert('일시적 오류가 발생했습니다. 다시 시도해주세요.');
      });
  };

  const connectStomp = (roomId) => {
    stomp.connect(
      {},
      () => {
        stomp.subscribe('/sub/chat/room/' + roomId, (chat) => {
          console.log('msg arrived');

          if (studentNumber === null) {
            history.push('/');
            return;
          }

          var content = JSON.parse(chat.body);

          addMsgData(num, content.name, content.userId, content.message);
          scrollToBottom();
        });
      },
      [],
    );
  };

  const scrollToBottom = () => {
    scrollRef.current.scrollIntoView({ behavior: 'smooth' });
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      sendMsg();
    }
  };

  function sendMsg() {
    const text = msgInput.current.value;

    if (text === '') {
      return;
    }

    if (studentNumber === null) {
      history.push('/');
      return;
    }

    stomp.send(
      '/pub/chat/message',
      {},
      JSON.stringify({
        roomId: window.sessionStorage.getItem('roomId'),
        name: userName,
        userId: userId,
        message: text,
      }),
    );

    msgInput.current.value = '';
  }

  return (
    <div style={{ width: '100%' }}>
      <VerticalHeader />
      <HorizontalHeader />

      <div id="chatRoomBody">
        <div id="emptySpace1" />

        <div className="secondHorizontalNav">
          <h3 style={{ color: '#008cff' }}> 채팅방 목록</h3>
          <div>
            <div>{listData()}</div>
          </div>
        </div>

        <div id="mainChatting">
          <h3 style={{ color: '#008cff' }}>대화하기</h3>

          <div id="taChattingSpace">
            {chatData()}
            <div ref={scrollRef}></div>
          </div>

          <div id="inputForm">
            <input
              id="taMsgInput"
              ref={msgInput} //border: solid 2px #990000;
              onKeyPress={handleKeyPress}
            ></input>
            <button id="taMsgBnt" onClick={() => sendMsg()}>
              전 송
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ taChats, login }) => {
  //console.log(taChats.chats);

  return {
    chatsData: taChats.chats,
    list: taChats.list,
    num: taChats.num,
    roomNum: taChats.roomNum,
    loginState: login.type,
    userName: login.userName,
    userId: login.userId,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    fetchChatData: () => dispatch(fetchChatData()),
    addMsgData: (id, name, userId, msg) =>
      dispatch(addMsgData(id, name, userId, msg)),
    addRoomData: (id, roomId, title, des) =>
      dispatch(addRoomData(id, roomId, title, des)),
    getBotResponse: (msg) => dispatch(getBotResponse(msg)),
    changeUserId: (id) => dispatch(changeUserId(id)),
    changeUserName: (name) => dispatch(changeUserName(name)),

    changeType: (type) => dispatch(changeType(type)),
    changeLoadingState: (props) => dispatch(changeLoadingState(props)),
    onLoginSuccess: (props) => dispatch(onLoginSuccess(props)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(TaChatRoom);
