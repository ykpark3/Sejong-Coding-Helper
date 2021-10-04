package com.example.testlocal.service;

import com.example.testlocal.domain.dto.ChatDTO;
import com.example.testlocal.domain.dto.ChatDTO2;
import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.entity.Room;
import com.example.testlocal.exception.InvalidChatIdException;
import com.example.testlocal.exception.InvalidRoomIdException;
import com.example.testlocal.repository.ChatRepository;
import com.example.testlocal.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repository;
    private final UserService2 userService;
    private final RoomService roomService;

    public Chat create(ChatDTO2 chatDTO){
        Chat chat = new Chat(chatDTO, roomService, userService);
        return repository.save(chat);
    }

    public List<Chat> findAll(){
        return repository.findAll();
    }

    public Chat findById(Long id){
        return repository.findById(id).orElseThrow(()-> new InvalidChatIdException());
    }

    public void deleteChat(Long id) {
        repository.deleteById(id);
    }
}
