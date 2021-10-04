package com.example.testlocal.service;

import com.example.testlocal.domain.dto.ChatDTO2;
import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.entity.User;
import com.example.testlocal.exception.InvalidChatIdException;
import com.example.testlocal.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, String> findById2(Map<String, String> chat, Long id) {

        Chat checkedChat = repository.findById(id).orElseThrow(()-> new InvalidChatIdException());

        Map<String, String> map = new HashMap<>();
        map.put("id", checkedChat.getId().toString());
        map.put("message", checkedChat.getMessage().toString());
        map.put("UserId",checkedChat.getUser().toString());
        map.put("createTime",checkedChat.getCreateTime().toString());
        map.put("roomId",checkedChat.getRoom().toString());

        return map;
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
