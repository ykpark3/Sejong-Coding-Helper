package com.example.testlocal.repository;

import com.example.testlocal.domain.dto.AssistantDTO;
import com.example.testlocal.domain.entity.Assistant;
import com.example.testlocal.domain.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, Long> {

    @Query(value = "select student_email from assistant where user_id = (select id from user where student_number = ?1)", nativeQuery = true)
    List<String> findAllStudentEmailByStudentNumber(String StudentNumber);

}
