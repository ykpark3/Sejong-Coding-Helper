from flask import Flask
from flask import request
from config.DatabaseConfig import *
from Database import Database
from Preprocess import Preprocess
from models.intent.IntentModel import IntentModel
from models.ner.NerModel import NerModel
from GiveAnswer import GiveAnswer
import os
from konlpy.tag import Komoran
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from re import match

app = Flask(__name__)

############# chatbot #############

# 전처리 객체 생성
p = Preprocess(word2index_dic= '/chatbot/train_tools/dict/chatbot_dict.bin',
               userdic= '/chatbot/utils/user_dic.tsv')

# 질문/답변 학습 DB 연결 객체 생성
db = Database(
    host=DB_HOST, user=DB_USER, password=DB_PASSWORD, db_name=DB_NAME
)
db.connect()  # DB 연결

intent = IntentModel(model_name= '/chatbot/models/intent/intent_model.h5', preprocess=p)
ner = NerModel(model_name= '/chatbot/models/ner/ner_model.h5', preprocess=p)

question = GiveAnswer(db=db)

############# recommend #############

#komoran 선언 및 사전추가
# komoran = Komoran(userdic='/recommend/user_dic.txt')
#
# data = pd.read_csv('/recommend/dataset.csv', low_memory=False)
#
# #데이터셋에 삽입
# df = pd.DataFrame(data)
#
# #Description과 Title이 공백이면 데이터프레임에서 제거
# data = df[['타이틀(Title)', '설명(Description)']].dropna()

############# Server #############

@app.route('/predict/bot_response', methods=['POST'])
def predictBotResonse():
    msg = request.get_json()
    msg = msg.get("message")
    print(msg)

    result = question.give_answer(msg, intent, ner)
    return result

if __name__ == '__main__':
    app.run(debug=True)