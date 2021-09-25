
import {
  FETCH_CHATDATA,
  FETCH_CHATDATA_REQUEST,
  FETCH_CHATDATA_SUCCESS,
  FETCH_CHATDATA_FAILURE,
  ADD_CHATMSG,
  GET_BOT_RESPONSE,
} from './chatTypes';

// const fetChatData = () => {
//     return (dispatch) => {
//         // fetch("url")
//         // .then(response => response.json())
//         // .then(chatData =>console.log(chatData))
//         // .catch(error=>console.log(error))
//     }
// }

export const fetchChatData = () => {
    return {
        type: FETCH_CHATDATA_SUCCESS,
    };
}

export const addMsgData = (id, sender,msg) => {
    return{
      type: ADD_CHATMSG,
      data:{id:id,sender: sender, msg: msg}
    };
}

export const getBotResponse = (msg) => {


  msg = "엔샵 박태순 최고";

  return{
    type: GET_BOT_RESPONSE,
    data:{
      msg:msg
    }
  }
}
