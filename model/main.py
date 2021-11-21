from flask import Flask
from flask import request
from config.DatabaseConfig import *
from Database import Database
from Preprocess import Preprocess
from models.intent.IntentModel import IntentModel
from models.ner.NerModel import NerModel
from GiveAnswer import GiveAnswer
from recommend import Recommendation
import os
from konlpy.tag import Komoran
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from re import match

app = Flask(__name__)

############# chatbot #############

# 전처리 객체 생성
p = Preprocess(word2index_dic= os.getcwd() + '/chatbot/train_tools/dict/chatbot_dict.bin',
               userdic= os.getcwd() +'/chatbot/utils/user_dic.tsv')

# 질문/답변 학습 DB 연결 객체 생성
db = Database(
    host=DB_HOST, user=DB_USER, password=DB_PASSWORD, db_name=DB_NAME
)
db.connect()  # DB 연결

intent = IntentModel(model_name= os.getcwd() +'/chatbot/models/intent/intent_model.h5', preprocess=p)
ner = NerModel(model_name= os.getcwd() +'/chatbot/models/ner/ner_model.h5', preprocess=p)

question = GiveAnswer(db=db)

############# recommend #############

reco = Recommendation();
komoran = Komoran(userdic=os.getcwd() +'/recommend/user_dic.txt')
recoPreProcessing = reco.preProcess()

@app.route('/predict/bot_response', methods=['POST'])
def predictBotResonse():
    msg = request.get_json()
    msg = msg.get("message")
    print(msg)

    result_chatbot = question.give_answer(msg, intent, ner)

    test_data = reco.insertUserData(recoPreProcessing, komoran, msg)
    result_reco = reco.get_recommendations(test_data, len(test_data) - 1)

    result_dict = dict()
    result_dict["botMsg"] = result_chatbot
    result_dict["reco"] = result_reco
    print(result_dict)
    return result_dict

if __name__ == '__main__':
    app.run(debug=True)

