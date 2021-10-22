package com.example.testlocal.controller;

import com.example.testlocal.domain.dto.ChatbotRoomDTO;
import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.entity.ChatbotRoom;
import com.example.testlocal.domain.entity.Room;
import com.example.testlocal.service.ChatbotRoomService;
import com.example.testlocal.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin(origins = "http://3.141.167.159" , allowCredentials = "true")
public class ChatbotRoomController {

    private final ChatbotRoomService chatbotRoomService;

    @ResponseBody
    @PostMapping("/chatbotRoom")
    public ChatbotRoom createRoom(@RequestBody ChatbotRoomDTO requestDTO){
        return chatbotRoomService.create(requestDTO);
    }

    @PostMapping("/chatbotRoom/studentId")
    public List<ChatbotRoom> findAllRoomByStudentId(@CookieValue(name = "refreshToken", defaultValue = "-1") String refreshToken) {
        return chatbotRoomService.findAllRoomByStudentId(refreshToken);
    }

}

