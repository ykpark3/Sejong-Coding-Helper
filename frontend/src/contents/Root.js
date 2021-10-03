import React, { useEffect, useState } from 'react';
import { connect, useDispatch } from 'react-redux';
import axios from 'axios';
import { changeType } from '../redux/login/loginActions';
import { LOGIN_BEFORE, LOGIN_SUCCESS } from '../redux/login/loginTypes';
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

  if (isFirstRendering) {
    try {
      changeLoadingState(true);
      refreshLoginToken(loginCallback);
      changeFirstRendering(false);
    } catch (e) {
      console.log(e);
    }
  }

  return '';
};

const mapStateToProps = ({ login, views }) => {
  return {
    loginState: login.state,
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
