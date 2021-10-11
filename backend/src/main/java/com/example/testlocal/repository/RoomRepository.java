package com.example.testlocal.repository;

import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query(value = "select * from room where user_id = " +
            "(select id from user where student_number = ?1) or user2_id = (" +
            "select id from user where student_number = ?1) ", nativeQuery = true)
    List<Room> findAllRoomByStudentId(String studentNumber);

}