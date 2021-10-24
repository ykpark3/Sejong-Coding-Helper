package com.example.testlocal.controller;

import com.example.testlocal.config.Constants;
import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.entity.Assistant;
import com.example.testlocal.domain.entity.Room;
import com.example.testlocal.repository.RoomRepository;
import com.example.testlocal.service.AssistantService;
import com.example.testlocal.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@CrossOrigin(origins = Constants.URL , allowCredentials = "true")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/room")
    public List<Room> all() { return roomService.findAll();}

    @ResponseBody
    @PostMapping("/room")
    public Room createRoom(@RequestBody RoomDTO requestDTO){
        return roomService.create(requestDTO);
    }

    @GetMapping("/room/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @DeleteMapping("/room")
    public void deleteRoom(@PathVariable Long id) { roomService.deleteRoom(id);}

    @PostMapping("/room/studentId")
    public List<Room> findAllRoomByStudentId(HttpServletRequest request, @CookieValue(name = "refreshToken", defaultValue = "-1") String refreshToken) {

        List<Room> result = roomService.findAllRoomByStudentId(refreshToken);
        HttpSession session = request.getSession();
        String roomId = (String)session.getAttribute("roomId");

        // get한 세션이 없을 경우 => 첫 번째 채팅방으로 session set.
        if(roomId == null){
            session.setAttribute("roomId",String.valueOf(result.get(0).getId()));
        }
        return result;
    }

    @PostMapping("/room/roomSessionId")
    public String getRoomSessionId(HttpServletRequest request){

        HttpSession session = request.getSession();
        String roomId = (String)session.getAttribute("roomId");

        return roomId;
    }

    @PostMapping("/room/roomSessionId/{roomId}")
    public void setRoomSessionId(HttpServletRequest request,@PathVariable String roomId){

        HttpSession session = request.getSession();
        session.setAttribute("roomId",roomId);
    }

}
