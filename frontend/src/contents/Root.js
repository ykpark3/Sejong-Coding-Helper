import React, { useEffect, useState } from 'react';
import { connect, useDispatch } from 'react-redux';
import axios from 'axios';
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
    }
  }

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
};

export default connect(mapStateToProps, mapDispatchToProps)(Root);
