import traceback

from utils.FindAnswer import FindAnswer

class GiveAnswer:
    def __init__(self, db):
        self.db = db

    def give_answer(self, intent, ner):
        # 사용자 질문
        print("input = ")
        query = input()

        # 의도 파악
        predict = intent.predict_class(query)

        intent_name = intent.labels[predict]

        # 개체명 인식
        predicts = ner.predict(query)
        ner_tags = ner.predict_tags(query)

        # 답변 검색
        try:
            f = FindAnswer(self.db)
            answer_text = f.search(intent_name, ner_tags, predicts)

            if not answer_text:
                answer = "죄송해요, 무슨 말인지 모르겠어요."
            else:
                answer = f.tag_to_word(predicts, answer_text)

        except Exception as e:
            print("e = ", e)
            print(traceback.format_exc())
            answer = "죄송해요, 무슨 말인지 모르겠어요."

        print("답변 : ", answer)
