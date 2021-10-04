package com.example.testlocal.repository;

import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Override
    <S extends Chat> S save(S entity);

    Optional<Chat> findById(String id);
}
