package com.example.testlocal.controller;

import com.example.testlocal.domain.dto.ChatDTO2;
import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.entity.Room;
import com.example.testlocal.service.ChatService;
import com.example.testlocal.service.UserService2;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController2 {

    private final ChatService chatService;

    @GetMapping("/chat2")
    public List<Chat> all() { return chatService.findAll();}

    @ResponseBody
    @PostMapping("/chat2")
    public Chat createChat(@RequestBody ChatDTO2 requestDTO){
        return chatService.create(requestDTO);
    }

    @GetMapping("/chat2/{id}")
    public Chat getChat(@PathVariable Long id) {
        return chatService.findById(id);
    }

    @DeleteMapping("/chat2")
    public void deleteChat(@PathVariable Long id) { chatService.deleteChat(id);}

}
