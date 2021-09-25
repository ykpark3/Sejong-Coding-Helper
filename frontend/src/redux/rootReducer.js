import { combineReducers } from "redux";
import viewReducer from "./view/viewReducer";
import chatReducer from "./chat/chatReducers";
import loginReducer from "./login/loginReducers";

const rootReducer = combineReducers({
    views:viewReducer,
    chats:chatReducer,
    login:loginReducer
})

export default rootReducer
