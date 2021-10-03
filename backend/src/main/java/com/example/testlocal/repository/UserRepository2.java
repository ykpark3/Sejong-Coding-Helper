package com.example.testlocal.repository;

import com.example.testlocal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository2 extends JpaRepository< User, Long> {
}
