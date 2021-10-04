<<<<<<< HEAD
import {LOGIN_SUCCESS, LOGIN_FAIL, LOGIN_PENDING, LOGIN_BEFORE, LOGIN_ORIGIN} from './loginTypes';

const initialState = {

    type: LOGIN_ORIGIN,
    id: ''
=======
import { LOGIN_SUCCESS, LOGIN_FAIL, LOGIN_PENDING, LOGIN_BEFORE,LOGIN_ORIGIN,CHANGE_SIGNUP_AUTH } from './loginTypes';

const initialState = {
    
    signupAuth:false,
    type:LOGIN_ORIGIN,
    id:''
>>>>>>> upstream/master
};

const loginReducer = (state = initialState, action) => {
    const {type, data} = action;

    switch (type) {

<<<<<<< HEAD
        case LOGIN_PENDING:
            return {
                ...state,
                type: LOGIN_PENDING,
            }
        case LOGIN_SUCCESS:
            return {
                ...state,
                type: LOGIN_SUCCESS,
            }
        case LOGIN_FAIL:
            return {
                ...state,
                type: LOGIN_FAIL
            }
        case LOGIN_BEFORE:
            return {
                ...state,
                type: LOGIN_BEFORE
            }
=======
    case LOGIN_PENDING:
        return{
            ...state,
            type:LOGIN_PENDING,
        }
    case LOGIN_SUCCESS:
        return {
            ...state,
            type:LOGIN_SUCCESS,
        }
    case LOGIN_FAIL:
        return{
            ...state,
            type:LOGIN_FAIL
        }
    case LOGIN_BEFORE:
        return{
            ...state,
            type:LOGIN_BEFORE
        }
    case CHANGE_SIGNUP_AUTH:
        return{
            ...state,
            signupAuth:data.signupAuth,
        }
>>>>>>> upstream/master

        default:
            return state;
    }
};

export default loginReducer;
