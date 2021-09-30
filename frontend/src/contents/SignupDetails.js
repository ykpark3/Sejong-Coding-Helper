import React, { useState } from 'react';
import HorizontalHeader from './HorizontalHeader';
import VerticalHeader from './VerticalHeader';
import '../css/Signup.css';
import axios from 'axios';
import { API_BASE_URL } from './utils/Constant';

const SignupDetails = () => {
  return (
    <div id="signupMainContainer">
      <VerticalHeader />
      <HorizontalHeader />
      <div id="signupBox">
        <img src="img/logo.png" />
        <h3>Sejong Coding Helper 회원가입</h3>
      </div>
    </div>
  );
};

export default SignupDetails;
