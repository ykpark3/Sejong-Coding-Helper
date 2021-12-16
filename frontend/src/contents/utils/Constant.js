//로컬에서 사용할시 주석을 교체하시옹

// export const BASE_URL = 'https://www.sju-coding-helper.site'
// export const API_BASE_URL = 'https://www.sju-coding-helper.site:8080'
// export const API_CHATBOT_URL = 'https://www.sju-coding-helper.site'
// export const API_COMPILER_URL = 'https://www.sju-coding-helper.site'

export const BASE_URL = 'http://localhost:3000'
export const API_BASE_URL = 'http://localhost:8080'
export const API_COMPILER_URL = 'http://localhost:8080'
export const API_CHATBOT_URL = 'http://localhost:8080'

export const CHATBOT_ID = 4
export const C_COMPILER_BASE_CODE = "#include <stdio.h>\n\nint main(void)\n{\n\tprintf(\"Hello, World\");\n\treturn 0;\n}"
export const P_COMPILER_BASE_CODE = "print('Hello, world!')"

export const TIP_TEXT = "SJ HELPER에게 질문하는 TIP!\n" +
"1. 키워드 위주, 명사 위주로 간결한 질문을 하면 더 정확한 답변을 받을 수 있어요.\n\n" +

"질문 예시\n" + 
"- 함수를 사용하는 법 X => 함수 사용 O\n"+
"- 구조체 변수를 선언하기 X => 구조체 변수 선언 O\n\n"+

"2. 질문은 한 번에 한 주제로만!\n\n"+

"질문 예시\n"+
"- 가정법이랑 반복문이 뭐야 X => 가정법 O\n"+
"- 가정법, 반복문 X => 가정법 O\n\n"+

"3. C언어와 Python 질문 채팅방이 분리돼 있어요!\n\n"+
"적절한 언어의 채팅방에서 질문해보세요!";

export const FAQ_Q_1 =  "세종 코딩 헬퍼는 누가, 왜 개발했나요?";
export const FAQ_A_1 =   "세종 코딩 헬퍼는 세종대학교 소융대 학생 4명으로 구성된 'Team EnTrue' 에서 개발했습니다. 코딩에 익숙하지 않은 세종대학교 학생들에게 코딩 공부에 도움을 주고 TA조교와의 원할한 소통을 도와주기 위해 개발하게 됐습니다.";

export const FAQ_Q_2 = "챗봇에게 질문 어떻게 하나요? 챗봇에게 질문하는 팁을 알려주세요!";
export const FAQ_A_2 = "좌측 메뉴의 챗봇과 대화하기 기능을 통해서 챗봇에게 코딩 질문을 할 수 있어요! 이 때, 질문할 코딩 언어를 C언어, Python 언어 둘중 하나를 선택하시고 질문하면됩니다. 챗봇이 질문을 잘 인식하지 못하는 경우도 있답니다. 이 때는 질문창에 'HELP'를 입력해서 질문하는 팁을 확인해보시고 조금 더 챗봇이 알아듣기 쉽게 질문해보세요!";

export const FAQ_Q_3 = "챗봇과 채팅하기 기능에서 실시간 인기 키워드가 무엇인가요?";
export const FAQ_A_3 = "세종 코딩 헬퍼만의 실시간 검색어 순위 시스템입니다. 실시간으로 가장 많이 검색되는 검색 키워드들을 보여줍니다.";

export const FAQ_Q_4 = "챗봇과 채팅하기 기능에서 질문 추천은 무엇인가요?";
export const FAQ_A_4 = "챗봇에게 질문을 하시면 해당 질문과 연관된 질문 키워드들을 추천해줍니다. 질문에 대한 답변의 내용으로 학습이 부족한 경우, 추천 질문 키워드들을 통해 추가학습이 가능합니다.";

export const FAQ_Q_5 = "챗봇 대답할 수 있는 학습 범위는 어느정도인가요?";
export const FAQ_A_5 = "세종 코딩 헬퍼는 세종대학교 1학년 코딩 수업의 정규 수업 내용을 기반으로 합니다. 그렇기 때문에 해당 범위 내에서 가장 최선의 답을 줄 수 있어요. 답변 데이터들은 개발진이 손수 정성껏 제작한 데이터입니다.";

export const FAQ_Q_6 = "TA조교님과 채팅을 하고 싶어요!";
export const FAQ_A_6 = "가입을 완료하신 후, 담당 TA 조교가 방을 생성했다면 자동으로 채팅방이 생성됩니다.  방이 없다면 아직 TA조교가 채팅방을 생성하지 않은 것입니다.";

export const FAQ_Q_7 = "저는 TA조교 입니다. TA 조교 자격을 어떻게 얻죠?";
export const FAQ_A_7 = "TA조교라면 가입을 완료하신 후, 좌측 메뉴의 문의 사항을 통해서 TA조교 자격을 신청하실 수 있습니다. 문의 사항을 참고해주세요.";

export const FAQ_Q_8 = "웹 컴파일러(코딩하기)는 어떻게 사용하나요?";
export const FAQ_A_8 =  "웹 컴파일러는 로그인을 하신 후, 좌측 메뉴의 코딩하기 메뉴에서 사용가능합니다. 코딩할 언어를 설정하신 후, 코드와 입력값을 입력하고 실행시키면 결과값을 확인할 수 있어요. 이 때, 코드와 입력값 그리고 결과값까지 TA조교에게 편리하게 전송하여 질문이 가능합니다.";

export const FAQ_Q_9 = "문의사항 또는 피드백을 남기고 싶어요!";
export const FAQ_A_9 = "좌측 메뉴의 문의 사항을 통해서 사이트를 이용하는 중 발생한 문제에 대한 문의나 피드백을 남겨주시면 최대한 빠르게 확인한 후 조치하도록 하겠습니다.";

