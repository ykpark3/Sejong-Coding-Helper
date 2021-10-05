package com.example.testlocal;

import com.example.testlocal.domain.entity.User;
import com.example.testlocal.repository.UserRepository2;
import com.example.testlocal.service.UserService2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class TestlocalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestlocalApplication.class, args);
    }
}
