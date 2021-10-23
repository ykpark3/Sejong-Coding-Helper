import React from 'react';
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import Editor from '@monaco-editor/react';
import '../css/CodingEditor.css';

const Curriculum = () => {
  return (
    <div id="editorContainer">
      <VerticalHeader />
      <HorizontalHeader />

      <div id="editorBox">
        <h1>코딩하기</h1>

        <Editor className="codingEditor"
          theme="vs-dark"
        height="90vh"
          defaultLanguage="c++"
          defaultValue="// some comment"
        />
      </div>
      <div id="outputBox"><h1>qwe</h1></div>
    </div>
  );
};

export default Curriculum;
