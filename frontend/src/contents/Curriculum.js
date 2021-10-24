import React, { useRef } from 'react';
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import Editor from '@monaco-editor/react';
import '../css/CodingEditor.css';
import axios from 'axios';
import { API_BASE_URL,API_COMPILER_URL } from './utils/Constant';
const Curriculum = () => {
  const editorRef = useRef(null);

  function showValue() {
    //alert(editorRef.current.getValue());
    const code = editorRef.current.getValue();
    axios
      .post(
        API_COMPILER_URL + '/compiler/c',
        { code: code },
        {
          headers: {
            'Content-type': 'application/json',
            Accept: 'application/json',
          },
          withCredentials: true,
        },
      )
      .then((res) => {
        console.log(res.data);
      })
      .catch((res) => {
        console.log(res);
        alert('일시적 오류가 발생했습니다. 다시 시도해주세요.');
      });
  }

  function handleEditorDidMount(editor, monaco) {
    editorRef.current = editor;
  }

  return (
    <div id="editorContainer">
      <VerticalHeader />
      <HorizontalHeader />

      <div id="editorBox">
        <div id="codingTopNav">
          <h1>코딩하기</h1>
          <button
            onClick={() => {
              showValue();
            }}
          >
            컴파일
          </button>
        </div>

        <div id="codingInnerBox">
          <Editor
            className="codingEditor"
            theme="vs-dark"
            defaultLanguage="c"
            onMount={handleEditorDidMount}
            defaultValue="//coding here"
          />
        </div>
      </div>
      <div id="outputBox">
        <h1>입력값 설정</h1>
        <div id="inputLine"></div>
        <h1>결 과</h1>
        <div id="outputLine">
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>            <h3>qweqwe</h3>
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>            <h3>qweqwe</h3>
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>
            <h3>qweqwe</h3>            <h3>qweqwe</h3>
            <h3>qweqwe</h3>

        </div>
      </div>
    </div>
  );
};

export default Curriculum;
