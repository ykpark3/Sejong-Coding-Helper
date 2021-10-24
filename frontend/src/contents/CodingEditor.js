import React, { useRef, useState } from 'react';
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import Editor from '@monaco-editor/react';
import '../css/CodingEditor.css';
import axios from 'axios';
import {
  API_BASE_URL,
  API_COMPILER_URL,
  C_COMPILER_BASE_CODE,
  P_COMPILER_BASE_CODE,
} from './utils/Constant';

const CodingEditor = () => {
  const editorRef = useRef(null);
  const [codeInput, setCodeInput] = useState('');
  const [codeOutput, setCodeOutput] = useState('');
  const [codeLang, setCodeLang] = useState('c');
  const [baseCode, setBaseCode] = useState(C_COMPILER_BASE_CODE);

  function showValue() {
    //alert(editorRef.current.getValue());
    const code = editorRef.current.getValue();
    setCodeOutput(''); // 결과창 초기화.

    axios
      .post(
        API_COMPILER_URL + '/compiler/c',
        { code: code, input: codeInput },
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
        setCodeOutput(res.data);
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

          <div id="compilerBntGroup">
            <p className="complierLangRadioBntP">C</p>
            <input
              className="complierLangRadioBnt"
              type="radio"
              value="c"
              checked={codeLang === 'c'}
              onChange={(e) => {
                setCodeLang(e.target.value);
                setBaseCode(C_COMPILER_BASE_CODE);
              }}
            ></input>
            <div style={{ width: '20px' }}></div>
            <p className="complierLangRadioBntP">P</p>
            <input
              className="complierLangRadioBnt"
              type="radio"
              value="python"
              checked={codeLang === 'python'}
              onChange={(e) => {
                setCodeLang(e.target.value);
                setBaseCode(P_COMPILER_BASE_CODE);
              }}
            />

            <button
              onClick={() => {
                showValue();
              }}
            >
              실 행
            </button>
          </div>
        </div>

        <div id="codingInnerBox">
          <Editor
            className="codingEditor"
            theme="vs-dark"
            language={codeLang}
            onMount={handleEditorDidMount}
            value={baseCode}
            
          />
        </div>
      </div>
      <div id="outputBox">
        <h1>입력값 설정</h1>

        <textarea
          id="inputLine"
          onChange={(e) => {
            setCodeInput(e.target.value);
          }}
        ></textarea>
        <h1>결 과</h1>
        <textarea id="outputLine" value={codeOutput} readOnly></textarea>
      </div>
    </div>
  );
};

export default CodingEditor;
