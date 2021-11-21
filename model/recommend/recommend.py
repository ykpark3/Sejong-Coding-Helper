#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity


# In[2]:


from konlpy.tag import Komoran

#komoran 선언 및 사전추가
komoran = Komoran(userdic='user_dic.txt')

# text = 'for문이 뭐야? 반복문 사용하는 방법 알려줘.'
text = '지역변수 선언 어떻게 해?'
print(komoran.nouns(text))


# In[3]:


# 문장 -> 단어(nouns)
from re import match

nouns = ""
for noun in komoran.nouns(text):
        if len(str(noun)) >= 2 and (match('[^방법]', noun)) :
            nouns = nouns + ' ' + noun 
    
nouns


# In[4]:


data = pd.read_csv('dataset.csv', low_memory=False)

data[['타이틀(Title)']].head()
data[['설명(Description)']].head()


# In[5]:


#데이터셋에 삽입
df = pd.DataFrame(data)

#Description과 Title이 공백이면 데이터프레임에서 제거
data = df[['타이틀(Title)', '설명(Description)']].dropna()
data


# In[6]:


#사용자의 문장 데이터셋에 삽입
new_data = {
    '타이틀(Title)': 'user question',
    '설명(Description)': nouns
}
data = data.append(new_data, ignore_index = True)
data


# In[7]:


#Decscription에 대해 tf-idf 수행

tfidf = TfidfVectorizer()
tfidf_matrix = tfidf.fit_transform(data['설명(Description)'].values.astype('U'))
print(tfidf_matrix.shape)


# In[8]:


#코사인 유사도 구하기
cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)
print(cosine_sim)

indices = pd.Series(data.index, index=data['타이틀(Title)']).drop_duplicates()
print(indices.head())
idx = indices['user question']


# In[9]:


#사용자의 질문에 대해 코사인 유사도를 이용하여
#가장 유사도가 비슷한 질문을 찾아내는 함수
def get_recommendations(idx):
    #선택한 질문의 인덱스 구하기
    #idx = indices[question]
    
    #모든 질문에 대해 해당 질문과의 유사도 구하기
    sim_scores = list(enumerate(cosine_sim[idx]))
    
    #유사도에 따라 질문들 정렬
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    
    #가장 유사한 5개 질문 받아오기
    sim_scores = sim_scores[1:5]
    
    #가장 유사한 5개의 질문의 인덱스 받아오기
    ques_indices = []
    ques_indices = [i[0] for i in sim_scores]
    
    #가장 유사한 5개의 질문 리턴
    return data['타이틀(Title)'].iloc[ques_indices]


# In[12]:


print(len(data))
print(data.loc[len(data) - 1])

#질문 추천 받기
print(get_recommendations(len(data) - 1))


# In[11]:


# #사용자 질문 데이터를 데이터셋에서 삭제 (마지막행 삭제)
# print(len(data))
# print(data.loc[len(data) - 1])
# data = data.drop(len(data) - 1)
# print(len(data))

