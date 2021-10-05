package com.example.testlocal.controller;

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

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
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

}
