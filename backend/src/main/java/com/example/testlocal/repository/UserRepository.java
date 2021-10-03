package com.example.testlocal.repository;

import com.example.testlocal.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    <S extends UserEntity> S save(S entity);

    Optional<UserEntity> findById(String id);
}
