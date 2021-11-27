package com.example.testlocal.service;

import com.example.testlocal.domain.entity.ChatbotTrainDataC;
import com.example.testlocal.repository.ChatbotTrainDataCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatbotTrainDataService {

    private final ChatbotTrainDataCRepository chatbotTrainDataCRepository;

    public List<ChatbotTrainDataC> findTop10TrainData(){
        return chatbotTrainDataCRepository.findTop15ByOrderByCountDesc();
    }
}
