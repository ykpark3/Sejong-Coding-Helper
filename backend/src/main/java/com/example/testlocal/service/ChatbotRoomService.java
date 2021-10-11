package com.example.testlocal.service;

import com.example.testlocal.domain.dto.ChatbotDTO;
import com.example.testlocal.domain.dto.ChatbotRoomDTO;
import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.entity.ChatbotRoom;
import com.example.testlocal.domain.entity.Room;
import com.example.testlocal.domain.entity.User;
import com.example.testlocal.exception.InvalidUserIdException;
import com.example.testlocal.repository.ChatbotRepository;
import com.example.testlocal.repository.ChatbotRoomRepository;
import com.example.testlocal.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatbotRoomService {

    private final ChatbotRoomRepository repository;
    private final UserService2 userService;

    public ChatbotRoom create(ChatbotRoomDTO roomDTO){
        ChatbotRoom room = new ChatbotRoom(roomDTO, userService);
        return repository.save(room);
    }

    public List<ChatbotRoom> findAllRoomByStudentId(String studentId){
        return repository.findAllRoomByStudentId(studentId);
    }

    public ChatbotRoom findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidUserIdException());
    }
}
