import React, { useState, useEffect } from 'react';
import HorizontalHeader from './HorizontalHeader';
import VerticalHeader from './VerticalHeader';
import '../css/Signup.css';
import axios from 'axios';
import { API_BASE_URL } from './utils/Constant';
import { connect, useDispatch } from 'react-redux';
import { changeSignupAuth } from '../redux/login/loginActions';

const SignupDetails = ({ changeSignupAuth }) => {

  const [isCorrectName, setCorrectName] = useState(false);
  const [isCorrectStuId, setCorrectStuId] = useState(false);
  cosnt[isCorrectPw, setCorrectPw] = useState(false);
  cosnt[isCorrectPwCheck, setCorrectPwCheck] = useState(false);

  useEffect(() => {
    return () => {
      changeSignupAuth(false);
    };
  });

  const onClickSignupBnt = () => {
    const reg_name = /^[가-힣]{2,4}$/;
    
  }

  return (
    <div id="signupMainContainer">
      <VerticalHeader />
      <HorizontalHeader />
      <div id="signupBox">
        <img src="img/logo.png" />
        <h3>Sejong Coding Helper 회원가입</h3>
        <div id="signupForm">
          <p className="smallTitle">이름</p>
          <input className="smallInput"></input>

          <p className="smallTitle">학번(아이디)</p>
          <p className="smallNotice">
            *채팅 매칭을 위해서 정확한 학번을 입력해주세요.
          </p>
          <input className="smallInput"></input>

          <p className="smallTitle">비밀 번호</p>
          <input className="smallInputPassword" type="password"></input>

          <p className="smallTitle">비밀 번호 확인</p>
          <input className="smallInputPassword" type="password"></input>

          <p className="smallTitle">세종대 이메일</p>
          <p className="smallNotice">*비밀번호 분실시 이용됩니다.</p>
          <input className="smallInput" ></input>

          <button onClick={() => {onClickSignupBnt();}}>가입 완료</button>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ login }) => {
  return {
    signupAuth: login.signupAuth,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    changeSignupAuth: (props) => dispatch(changeSignupAuth(props)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(SignupDetails);
