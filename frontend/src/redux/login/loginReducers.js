import { LOGIN, LOGIN_SUCCESS, LOGIN_FAIL } from './loginTypes';

const initialState = {
    state:'',
    id:''
};

const loginReducer = (state = initialState, action) => {
  const { type, data } = action;

  switch (type) {

    case LOGIN_SUCCESS:
        return {
            ...state,
            state:LOGIN_SUCCESS,
            id : data.id
        }
    case LOGIN_FAIL:
        return{
            ...state,
            state:LOGIN_FAIL
        }

    default:
      return state;
  }
};

export default loginReducer;
