import {
  FETCH_CHATDATA,
  FETCH_CHATDATA_REQUEST,
  FETCH_CHATDATA_SUCCESS,
  FETCH_CHATDATA_FAILURE,
  ADD_BOT_CHATMSG,
  GET_BOT_RESPONSE,
  CHAGNE_CROOM_ID,
  CHAGNE_PROOM_ID,
  CLEAR_CHAT_LIST,
  CHAGNE_NOW_BOTROOM_ID,
} from './botChatTypes';

const initialState = {
  num: 0,
  nowRoomId:-1,
  cRoomId: -1,
  pRoomId: -1,
  chats: [

    //EX
    // {
    //   id: 1,
    //   sender: 'bot',
    //   msg: 'hihi',
    // },
    // {
    //   id: 2,
    //   sender: 'user',
    //   msg: 'hihihihihi',
    // },
  ],
  list: [
    {
      id: 1,
      title: 'C언어 변수',
      des: '기본적인 변수 선언, 변수 할당',
    },
    {
      id: 2,
      title: '관계 연산자',
      des: '관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.',
    },
    {
      id: 3,
      title: '조건문의 종류',
      des: '조건식에 따라 실행하는 명령문의 흐름 제어문',
    },
    {
      id: 4,
      title: '오류 코드 printf',
      des: '주로 C코드 출력문에서의 문법 오류',
    },
    {
      id: 5,
      title: 'C언어 변수',
      des: '기본적인 변수 선언, 변수 할당',
    },
    {
      id: 6,
      title: '관계 연산자',
      des: '관계 연산자의 결과 확인, 변수에 값을 저장하는 대입 연산자.',
    },
    {
      id: 7,
      title: '조건문의 종류',
      des: '조건식에 따라 실행하는 명령문의 흐름 제어문',
    },
    {
      id: 8,
      title: '오류 코드 printf',
      des: '주로 C코드 출력문에서의 문법 오류',
    },
  ]
};

const botChatReducer = (state = initialState, action) => {
  const { type, data } = action;

  switch (type) {
    case FETCH_CHATDATA_SUCCESS:
      return state;

    case CHAGNE_NOW_BOTROOM_ID:
      return{
        ...state,
        nowRoomId:data.nowRoomId,
      }

    case CLEAR_CHAT_LIST:
      return {
        ...state,
        chats:[],
      }

    case CHAGNE_CROOM_ID:
      return {
        ...state,
        cRoomId: data.cRoomId
      };

    case CHAGNE_PROOM_ID:
      return {
        ...state,
        pRoomId: data.pRoomId
      };

    case ADD_BOT_CHATMSG:
      //state.num= state.num + 1;
      return {
        ...state,
        chats: state.chats.concat(
          {
            id: state.num + 1,
            sender: data.sender,
            msg: data.msg,
            time: data.time,
          },
        ),
        num: state.num + 1,
      };

    case GET_BOT_RESPONSE:
      return {
        ...state,
        chats: state.chats.concat(
          {
            id: state.num + 1,
            sender: 'bot',
            msg: data.msg,
            reco:data.reco,
            time:data.time
          },
        ),
        num: state.num + 1,
      };

    default:
      return state;
  }
};

export default botChatReducer;
