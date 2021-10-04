import React, {useEffect, useState} from 'react';
import {connect, useDispatch} from 'react-redux';
import axios from 'axios';
<<<<<<< HEAD
import {changeType} from '../redux/login/loginActions';
import {LOGIN_BEFORE, LOGIN_SUCCESS} from '../redux/login/loginTypes';
import {refreshLoginToken} from './utils/LoginUtils';

const JWT_EXPIRY_TIME = 24 * 3600 * 1000; // 만료 시간 (24시간 밀리 초로 표현)

var getCookie = function (name) {
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value ? value[2] : null;
};

const Root = ({loginState, changeType}) => {

    function loginCallback(type) {
        if (type === LOGIN_SUCCESS) {
            changeType(LOGIN_SUCCESS);
        } else if (type === LOGIN_BEFORE) {
            changeType(LOGIN_BEFORE);
        }
=======
import { changeType } from '../redux/login/loginActions';
import { LOGIN_BEFORE, LOGIN_SUCCESS,LOGIN_ORIGIN } from '../redux/login/loginTypes';
import { refreshLoginToken } from './utils/LoginUtils';
import { changeLoadingState, changeFirstRendering } from '../redux/view/viewActions';

const Root = ({ loginState,isFirstRendering, changeType, changeLoadingState, changeFirstRendering }) => {

  function loginCallback(type) {
    if (type === LOGIN_SUCCESS) {
      changeType(LOGIN_SUCCESS);
      changeLoadingState(false);
    } else if (type === LOGIN_BEFORE) {
      changeType(LOGIN_BEFORE);
      changeLoadingState(false);
>>>>>>> upstream/master
    }

<<<<<<< HEAD
    useEffect(() => {
        try {
            //   let data = getCookie("id");
            //   data = { id: data };
            //   if (data === null) {
            //     console.log("app silent requset fail");
            //     return;
            //   }
            //   console.log(data);
            //   axios
            //     .post('http://localhost:8080/refreshLoginToken', JSON.stringify(data), {
            //       headers: {
            //         'Content-Type': `application/json`,
            //       },
            //       withCredentials: true,
            //     })
            //     .then((res) => {
            //       console.log('login success');
            //       axios.defaults.headers.common['Authorization'] = 'Bearer ' + res.data;
            //       changeType(LOGIN_SUCCESS);
            //     })
            //     .catch((ex) => {
            //       console.log('app silent requset fail : ' + ex);
            //       changeType(LOGIN_BEFORE);
            //     })
            //     .finally(() => {
            //       //console.log('login request end');
            //     });
            // } catch (e) {
            //   console.log(e);
            // }

            refreshLoginToken(loginCallback);
        } catch (e) {
            console.log(e);
        }
    });

    return '';
};

const mapStateToProps = ({login}) => {
    return {
        loginState: login.state,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        login: (id, pwd) => dispatch(login(id, pwd)),
        changeType: (type) => dispatch(changeType(type)),
    };
=======
  // 새로고침 이후에서만 slient 로그인
  if (loginState==LOGIN_ORIGIN) {
    try {
      changeLoadingState(true);
      refreshLoginToken(loginCallback);
      // changeFirstRendering(false);
    } catch (e) {
      console.log(e);
    }
  }

  return null;
};

const mapStateToProps = ({ login, views }) => {
  return {
    loginState: login.type,
    isFirstRendering: views.isFirstRendering,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    login: (id, pwd) => dispatch(login(id, pwd)),
    changeType: (type) => dispatch(changeType(type)),
    changeLoadingState: (props) => dispatch(changeLoadingState(props)),
    changeFirstRendering: (props) => dispatch(changeFirstRendering(props))

  };
>>>>>>> upstream/master
};

export default connect(mapStateToProps, mapDispatchToProps)(Root);
