import React, {useState,useEffect}  from 'react';
import { Link } from 'react-router-dom';
import { connect, useDispatch } from 'react-redux';
import '../css/Login.css';
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import { login } from '../redux/login/loginActions';
import { LOGIN_SUCCESS } from '../redux/login/loginTypes';

const Login = ({loginState,login}) => {

  useEffect(() => {
    if(loginState === LOGIN_SUCCESS){
      window.alert('로그인 성공!');
    }
  });

  const [id,setId] = useState("");
  const [pwd,setPwd] = useState("");

  const logining = () => {
    if(id ==="" || pwd === ""){
      return;
    }

    console.log(login(id,pwd));
  }

  return (
    <div id="loginMainContainer">
      <VerticalHeader />
      <HorizontalHeader />

      <div id="loginBox">
        <img src="img/logo.png" />
        <h3>Sejong Coding Helper 로그인</h3>

        <h5>아이디</h5>
        <input id="idInput" placeholder="아이디" onChange= { (e) => {setId(e.target.value);}}></input>

        <h5>비밀 번호</h5>
        <input id="pwdInput" placeholder="비밀번호"  type="password" 
        onChange= { (e) => {setPwd(e.target.value);}}></input>

        <div id="textBox">
          <h4 className="contentsText1">회원 가입</h4>
          <h4 className="contentsText1">아아디 찾기</h4>
          <h4 className="contentsText1">비밀번호 찾기</h4>
          
        </div>

        <button onClick= {()=>{{logining();}}}>로그인</button>
      </div>
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
    login: (id, pwd) => dispatch(login(id, pwd)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);
