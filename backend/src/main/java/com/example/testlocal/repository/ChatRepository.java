package com.example.testlocal.repository;

import com.example.testlocal.domain.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
