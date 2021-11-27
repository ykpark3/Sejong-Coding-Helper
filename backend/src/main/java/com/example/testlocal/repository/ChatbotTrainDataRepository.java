package com.example.testlocal.repository;

import com.example.testlocal.domain.entity.ChatbotRoom;
import com.example.testlocal.domain.entity.ChatbotTrainData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatbotTrainDataRepository extends JpaRepository<ChatbotTrainData,Long> {

    List<ChatbotTrainData> findTop15ByOrderByCountDesc();

}
