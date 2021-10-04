import React, { useState, useEffect } from 'react';
import HorizontalHeader from './HorizontalHeader';
import VerticalHeader from './VerticalHeader';
import '../css/Signup.css';
import { useLocation,useHistory } from 'react-router';

const SignupComplete = () => {

    const location = useLocation();
    const name = location.state.name;
    const history = useHistory();

    return (
        <div id="signupMainContainer">
            <VerticalHeader />
            <HorizontalHeader />
            <div id="signupBox">
                <img src="img/logo.png" />
                <h3>Sejong Coding Helper 회원가입 완료</h3>
                <h3 style={{fontSize:'20px' , marginTop:'20px'}}>{name}님 반갑습니다.</h3>
                <h3 style={{fontSize:'20px'}}>이제 모든 서비스를 이용하실 수 있습니다!!</h3>
                <button style={{width:'300px', marginTop:'80px' , marginBottom:'30px'}}
                onClick={()=>{history.push('/')}}>메인으로</button>
            
            </div>
        </div>
    )
}

export default SignupComplete
