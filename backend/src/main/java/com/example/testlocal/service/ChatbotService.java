package com.example.testlocal.service;

import com.example.testlocal.domain.dto.ChatbotDTO;
import com.example.testlocal.domain.entity.Chatbot;
import com.example.testlocal.repository.ChatbotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatbotService {
    private final ChatbotRepository repository;
    private final UserService2 userService;
    private final ChatbotRoomService chatbotRoomService;

    public Chatbot create(ChatbotDTO chatbotDTO){
        Chatbot chat = new Chatbot(chatbotDTO, userService, chatbotRoomService);
        return repository.save(chat);
    }

    public List<Chatbot> findByRoomId(Long id) {
        return repository.findAllByRoomId(id);
    }

    }
