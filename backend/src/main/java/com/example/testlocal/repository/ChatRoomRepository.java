package com.example.testlocal.repository;


import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.dto.RoomDTO2;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//채팅방 생성 및 정보 조회 Repository
@Repository
public class ChatRoomRepository {
    private Map<String, RoomDTO2> chatRoomDTOMap;

    public ChatRoomRepository() {
        //임시 채팅방 데이터
        chatRoomDTOMap = Collections.unmodifiableMap(
                Stream.of(RoomDTO2.create("1", "1번방"),
                                RoomDTO2.create("2", "2번방"))
                        .collect(Collectors.toMap(RoomDTO2::getRoomNo, Function.identity())));
    }

    public RoomDTO2 findRoomById(String id){
        return chatRoomDTOMap.get(id);
    }

    public List<RoomDTO2> findAllRooms(){
        List<RoomDTO2> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    public RoomDTO2 createChatRoomDTO(String no, String name){
        RoomDTO2 room = RoomDTO2.create(no, name);
        chatRoomDTOMap.put(room.getRoomNo(), room);

        return room;
    }
}