import traceback

from utils.FindAnswer import FindAnswer

class GiveAnswer:
    def __init__(self, db):
        self.db = db

    def give_answer(self, msg, intent, ner, language):
        f = FindAnswer(self.db)

        # msg와 일치하는 title 값 있는지 확인
        answer_text = f.search_title(msg, language)

        # 비어있지 않으면
        if answer_text is not None:
            answer = answer_text

        else:
            # 의도 파악
            predict = intent.predict_intent_class(msg)

            intent_name = intent.labels[predict]

            # 개체명 인식
            predicts = ner.predict(msg)
            ner_tags = ner.predict_tags(msg)

            # 답변 검색
            try:
                answer_text = f.search(intent_name, ner_tags, predicts, language)

                if not answer_text:
                    answer = "죄송해요, 무슨 말인지 모르겠어요."
                else:
                    answer = f.tag_to_word(predicts, answer_text)

            except Exception as e:
                print("e = ", e)
                print(traceback.format_exc())
                answer = "죄송해요, 무슨 말인지 모르겠어요."

        print("답변 : ", answer)
