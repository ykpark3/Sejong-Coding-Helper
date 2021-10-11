package com.example.testlocal.repository;

import com.example.testlocal.domain.dto.AssistantDTO2;
import com.example.testlocal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository2 extends JpaRepository< User, Long> {
    @Override
    <S extends User> S save(S entity);

    Optional<User> findByStudentNumber(String studentNumber);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    @Query(value = "select name from user where student_number = ?1 " +
            "union select exists (" +
            "select * from assistant where user_id = (" +
            "select id from user where student_number = ?1))as isAssistant", nativeQuery = true)
    List<Map<String, Object>> findIsAssistantByStudentNumber(String studentNumber);

    @Query(value = "select id from user where student_number = ?1", nativeQuery = true)
    int findUserIdByStudentNumber(String studentNumber);

}
