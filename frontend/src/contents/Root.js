import React, { useEffect, useState } from 'react';
import { connect, useDispatch } from 'react-redux';
import axios from 'axios';
import { changeType } from '../redux/login/loginActions';
import { LOGIN_BEFORE, LOGIN_SUCCESS } from '../redux/login/loginTypes';
import { refreshLoginToken } from './utils/LoginUtils';

const JWT_EXPIRY_TIME = 24 * 3600 * 1000; // 만료 시간 (24시간 밀리 초로 표현)

var getCookie = function (name) {
  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value ? value[2] : null;
};

const Root = ({ loginState, changeType }) => {

  function loginCallback(type) {
    if (type === LOGIN_SUCCESS) {
      changeType(LOGIN_SUCCESS);
    } else if (type === LOGIN_BEFORE) {
      changeType(LOGIN_BEFORE);
    }
  }

  useEffect(() => {
    try {

      refreshLoginToken(loginCallback);
    } catch (e) {
      console.log(e);
    }
  });

  return '';
};

const mapStateToProps = ({ login }) => {
  return {
    loginState: login.state,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    login: (id, pwd) => dispatch(login(id, pwd)),
    changeType: (type) => dispatch(changeType(type)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Root);
