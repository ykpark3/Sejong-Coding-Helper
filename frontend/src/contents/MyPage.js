import React, { useState, useEffect } from 'react';
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import axios from 'axios';
import { connect, useDispatch } from 'react-redux';
import { changeType, onLoginSuccess } from '../redux/login/loginActions';
import Root from './Root';
import { LOGIN_BEFORE, LOGIN_SUCCESS } from '../redux/login/loginTypes';
import { API_BASE_URL } from './utils/Constant';
import { changeLoadingState } from '../redux/view/viewActions';
import { root2 } from './Root2';

const MyPage = ({
  history,
  loginState,
  onLoginSuccess,
  changeType,
  changeLoadingState,
}) => {


  useEffect(() => {

    // 동기로 리프래쉬토큰 검증.
    const auth = async () => {
      const result = await root2(onLoginSuccess,changeType,changeLoadingState);
      //console.log(result + ' three');
      if (result === 'success') {
        //adding user info
      }
    };

    auth();

  }, []);

  const logout = () => {
    axios
      .post(
        API_BASE_URL + '/userlogout',
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
        console.log(res.data);
        changeType(LOGIN_BEFORE);
        history.push('/');
      })
      .catch((res) => {
        // id or pwd 오류
        console.log(res);
      });
  };

  return (
    <div>
      <VerticalHeader />
      {/* <HorizontalHeader /> */}
      myPage입니다 ㅅㄱmyPage입니다 ㅅㄱmyPage입니다 ㅅㄱ
      <button style={{ zIndex: '999' }} onClick={() => logout()}>
        로그 아웃
      </button>
    </div>
  );
};

const mapStateToProps = ({ login }) => {
  return {
    loginState: login.state,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    changeType: (type) => dispatch(changeType(type)),
    changeLoadingState: (props) => dispatch(changeLoadingState(props)),
    onLoginSuccess: (props) => dispatch(onLoginSuccess(props)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(MyPage);
