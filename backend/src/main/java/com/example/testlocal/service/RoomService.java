package com.example.testlocal.service;

import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.entity.Room;
import com.example.testlocal.exception.InvalidRoomIdException;
import com.example.testlocal.repository.RoomRepository;
import com.example.testlocal.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final UserService2 userService;
    private final JwtTokenProvider jwtTokenProvider;

    public Room create(RoomDTO roomDTO){
        Room room = new Room(roomDTO, userService);
        return repository.save(room);
    }

    public List<Room> findAll(){
        return repository.findAll();
    }

    public Room findById(Long id){
        return repository.findById(id).orElseThrow(()-> new InvalidRoomIdException());
    }

    public List<Room> findAllRoomByStudentId(String refreshToken){
        String studentId = jwtTokenProvider.getUserPk(refreshToken);
        return repository.findAllRoomByStudentId(studentId);
    }

    public void deleteRoom(Long id) {
        repository.deleteById(id);

    }
}
