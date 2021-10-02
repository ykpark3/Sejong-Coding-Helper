import React, { useState, useEffect } from 'react';
import HorizontalHeader from './HorizontalHeader';
import VerticalHeader from './VerticalHeader';
import '../css/Signup.css';
import axios from 'axios';
import { API_BASE_URL } from './utils/Constant';
import { connect, useDispatch } from 'react-redux';
import { changeSignupAuth } from '../redux/login/loginActions';
import { useLocation } from 'react-router';

const SignupDetails = ({ changeSignupAuth, }) => {

  const location = useLocation();

  const [isCorrectName, setCorrectName] = useState(false);
  const [isCorrectStuId, setCorrectStuId] = useState(false);
  const [isCorrectPw, setCorrectPw] = useState(false);
  const [isCorrectPwCheck, setCorrectPwCheck] = useState(false);

  const [name, setName] = useState('');
  const [id, setId] = useState('');
  const [pw, setPw] = useState('');
  const [pwCheck, setPwCheck] = useState('');

  const email = location.state.email + "@sju.ac.kr";
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
    return () => {
      changeSignupAuth(false);
    };
  }, [pathname]);

  const onClickSignupBnt = () => {


    if (id === '' || pw === '' || pwCheck === '' || name === '') {
      alert("🙄❗❓ 회원가입 입력칸을 전부 입력해주세요. ❓❗🙄");
      return;
    }

    const reg_name = /^[가-힣]{2,5}$/;
    const reg_pw = /^[a-z0-9_.*?[#?!@$%^&*-]{4,20}$/;
    const reg_stunum = /^[0-9]{8}$/;

    if (!reg_name.test(name)) {
      alert("🙄❗❓ 이름을 다시 확인해주세요. ❓❗🙄");
      return;
    }

    if (!reg_stunum.test(id)) {
      alert("🙄❗❓ 아이디(학번)를 다시 확인해주세요. ❓❗🙄");
      return;
    }

    if (!reg_pw.test(pw)) {
      alert("🙄❗❓ 패스워드를 다시 확인해주세요. ❓❗🙄");
      return;
    }

    if (pw !== pwCheck) {
      alert("🙄❗❓ 패스워드와 패스워드 확인의 입력이 일치하지 않습니다. ❓❗🙄");
      return;
    }

    // 아이디 중복 체크

    // signup db등록 메소드.
    axios.post(
      API_BASE_URL + '/completeUserSignup',
      { name: name, id: id, pw: pw, email: email },
      {
        headers: {
          'Content-type': 'application/json',
          Accept: 'application/json',
        },
        withCredentials: true,
      },
    ).then((res) => {
      console.log(res.data);

      

    })
      .catch((res) => {
        console.log(res);
      });

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
          <input type="text" className="smallInput" maxLength='5' onChange={(e) => {
            setName(e.target.value);
          }}></input>

          <p className="smallTitle">학번(아이디)</p>
          <p className="smallNotice">
            *채팅 매칭을 위해서 정확한 학번을 입력해주세요.
          </p>
          <input className="smallInput" maxLength='8' onChange={(e) => {
            setId(e.target.value);
          }} onKeyPress={(e) => {
            if (!/[0-9]/.test(e.key)) {
              e.preventDefault();
            }
          }}></input>

          <p className="smallTitle">비밀 번호</p>
          <input className="smallInputPassword" maxLength='20' type="password" onChange={(e) => {
            setPw(e.target.value);
          }}></input>

          <p className="smallTitle">비밀 번호 확인</p>
          <input className="smallInputPassword" type="password" maxLength='20' onChange={(e) => {
            setPwCheck(e.target.value);
          }}></input>

          <p className="smallTitle">세종대 이메일</p>
          <p className="smallNotice">*비밀번호 분실시 이용됩니다.</p>
          <input className="smallInput" disabled value={email}></input>

          <button onClick={() => { onClickSignupBnt(); }}>가입 완료</button>
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
