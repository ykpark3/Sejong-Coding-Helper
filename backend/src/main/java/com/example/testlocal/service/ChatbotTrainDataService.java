package com.example.testlocal.service;

import com.example.testlocal.domain.entity.ChatbotTrainData;
import com.example.testlocal.repository.ChatbotTrainDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatbotTrainDataService {

    private final ChatbotTrainDataRepository repository;

    public List<ChatbotTrainData> findTop10TrainData(){
        return repository.findTop15ByOrderByCountDesc();
    }
}
