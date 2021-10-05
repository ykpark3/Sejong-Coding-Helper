package com.example.testlocal.controller;

import com.example.testlocal.domain.dto.ChatDTO2;
import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.entity.Room;
import com.example.testlocal.service.ChatService;
import com.example.testlocal.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;
    private final RoomService roomService;
    private final ChatService chatService;

    @GetMapping("/chat")
    public List<Chat> all() { return chatService.findAll();}

    @ResponseBody
    @PostMapping("/chat")
    public Chat createChat(@RequestBody ChatDTO2 requestDTO){
        return chatService.create(requestDTO);
    }

/*//    @PostMapping("/chat/{id}")
//    public Chat getChat(@PathVariable Long id) {
//        return chatService.findById(id);
//    }*/

    @PostMapping("/chat/{id}")
    public String hello(@RequestBody Map<String, Object> map, @PathVariable Long id) {
        map = chatService.findById2(id);
        return map.values().toString();
    }

    @PostMapping("/chat/roomId/{roomId}")
    public List<Chat> findByRoomId(@PathVariable Long roomId) {
        return chatService.findByRoomId(roomId);
    }



 /*   @PostMapping("/chat/{id}")
    public Chat getChat(@PathVariable Long id) {
        return chatService.findById(id);
    }*/

    @DeleteMapping("/chat")
    public void deleteChat(@PathVariable Long id) { chatService.deleteChat(id);}

    //WebSocketConfig에서 설정한 applicationDestinationPrefixes와
    // MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping("/chat/enter")
    public void enter(ChatDTO2 msg){
        msg.setMessage(roomService.findById(msg.getRoomId()).getTitle() + " 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + msg.getRoomId().toString(), msg);
    }

    @MessageMapping("/chat/message")
    public void message(ChatDTO2 msg){
        chatService.create(msg);
        template.convertAndSend("/sub/chat/room/" + msg.getRoomId().toString(), msg);
    }
}
