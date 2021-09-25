/* eslint-disable*/
import React, { Component } from 'react';
import './css/App.css';
import { BrowserRouter, Route, Link, Switch } from 'react-router-dom';
import ChatRoom from './contents/ChatRoom';
import Login from './contents/Login';
import Main from './contents/Main';
import { Provider } from 'react-redux'
import store from './redux/store';


function App() {
  return (

    <Provider store={store}>
      <div className="App">
        <BrowserRouter>
          <Route path="/" component={Main} exact />
          <Route path="/botchatroom" component={ChatRoom} />
          <Route path="/login" component={Login} />
        </BrowserRouter>
      </div></Provider>
  );
}

export default App;
