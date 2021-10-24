package com.example.testlocal.service;

import com.example.testlocal.domain.dto.ChatDTO2;
import com.example.testlocal.domain.dto.ChatbotDTO;
import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.entity.Chatbot;
import com.example.testlocal.domain.vo.GccCompiler;
import com.example.testlocal.exception.InvalidChatIdException;
import com.example.testlocal.repository.ChatRepository;
import com.example.testlocal.repository.ChatbotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
