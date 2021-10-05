package com.example.testlocal.repository;

import com.example.testlocal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository2 extends JpaRepository< User, Long> {
    @Override
    <S extends User> S save(S entity);

    Optional<User> findById(String id);

}
