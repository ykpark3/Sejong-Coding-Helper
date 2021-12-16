import React from 'react'
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import '../css/Qa.css';
import {
    FAQ_A_1, FAQ_Q_1, FAQ_A_2, FAQ_Q_2, FAQ_A_3, FAQ_Q_3, FAQ_A_4, FAQ_Q_4, FAQ_A_5, FAQ_Q_5
    , FAQ_A_6, FAQ_Q_6, FAQ_A_7, FAQ_Q_7, FAQ_A_8, FAQ_Q_8, FAQ_A_9, FAQ_Q_9
} from './utils/Constant';
const FAQ = () => {

    function FaqContent({ question, answer }) {

        return (
            <>
                <div className='question'>
                    <img src="img/faqq.png" />
                    <p>
                        {question}
                    </p>
                </div>
                <div className='answer'>
                    {answer}
                </div>

            </>
        )
    }

    return (
        <div>
            <VerticalHeader />
            <HorizontalHeader />

            <div id="qaBox">
                <img src="img/logo.png" />
                <h3>Sejong Coding Helper<br />자주 묻는 질문</h3>
                <div id="qaNoticeBox">
                    <FaqContent question={FAQ_Q_1} answer={FAQ_A_1} />
                    <FaqContent question={FAQ_Q_2} answer={FAQ_A_2} />
                    <FaqContent question={FAQ_Q_3} answer={FAQ_A_3} />
                    <FaqContent question={FAQ_Q_4} answer={FAQ_A_4} />
                    <FaqContent question={FAQ_Q_5} answer={FAQ_A_5} />
                    <FaqContent question={FAQ_Q_6} answer={FAQ_A_6} />
                    <FaqContent question={FAQ_Q_7} answer={FAQ_A_7} />
                    <FaqContent question={FAQ_Q_8} answer={FAQ_A_8} />
                    <FaqContent question={FAQ_Q_9} answer={FAQ_A_9} />
                </div>
            </div>

            <div style={{ width: '100%', height: '100px' }}>

            </div>

        </div>
    )
}

export default FAQ;
