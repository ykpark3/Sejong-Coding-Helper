import React from 'react';
import '../css/App.css';
import { Link } from 'react-router-dom';

const VerticalHeader = () => {
  return (
    <div id="headerBg">
      <Link to="/botchatroom">
        <div className="verNavItem">
          <img className="logoImg" src="img/logo.png" />
          <p
            className="header_title"
            style={{ color: '#ffffff', textDecoration: 'none' }}
          >
            챗봇과
            <br/>
            채팅하기
          </p>
          <hr className="header_line" />
        </div>
      </Link>

      <div className="verNavItem">
        <img className="logoImg" src="img/coding.png" />
        <p className="header_title">
          TA조교와
          <br />
          채팅하기
        </p>
        <hr className="header_line" />
      </div>

      <div className="verNavItem">
        <img className="logoImg" src="img/curri.png" />
        <p className="header_title">커리 큘럼</p>
        <hr className="header_line" />
      </div>

      <div className="verNavItem">
        <img className="logoImg" src="img/qa.png" />
        <p className="header_title">문의 사항</p>
        <hr className="header_line" />
      </div>

    </div>
  );
};

export default VerticalHeader;
