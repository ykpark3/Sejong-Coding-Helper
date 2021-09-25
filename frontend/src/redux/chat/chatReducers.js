import {
  FETCH_CHATDATA,
  FETCH_CHATDATA_REQUEST,
  FETCH_CHATDATA_SUCCESS,
  FETCH_CHATDATA_FAILURE,
  ADD_CHATMSG,
  GET_BOT_RESPONSE,
} from './chatTypes';

const initialState = {
  num: 4,
  items: [
    {
      id: 1,
      sender: 'bot',
      msg: 'hihi',
    },
    {
      id: 2,
      sender: 'user',
      msg: 'hihihihihi',
    },
    {
      id: 3,
      sender: 'bot',
      msg: '안녕ㅋㅋ',
    },
    {
      id: 4,
      sender: 'user',
      msg: '어쩔ㅋㅋ',
    },
  ],
};

const chatReducer = (state = initialState, action) => {
  const { type, data } = action;

  switch (type) {
    case FETCH_CHATDATA_SUCCESS:
      return state;

    case ADD_CHATMSG:
      //state.num= state.num + 1;
      return {
        ...state,
        items: state.items.concat(
          {
            id: state.num + 1,
            sender: data.sender,
            msg: data.msg,
          },
        ),
        num: state.num + 1,
      };

    case GET_BOT_RESPONSE:
      return {
        ...state,
        items: state.items.concat(
          {
            id: state.num + 1,
            sender: 'bot',
            msg: data.msg,
          },
        ),
        num: state.num + 1,
      };

    default:
      return state;
  }
};

export default chatReducer;
