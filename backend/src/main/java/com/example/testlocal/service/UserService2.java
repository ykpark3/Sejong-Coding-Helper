package com.example.testlocal.service;

import com.example.testlocal.domain.entity.User;
import com.example.testlocal.repository.UserRepository2;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService2 {

    private final UserRepository2 userRepository2;

    public Long create(){

        User user =
                User.builder()
                        .studentNumber("17011527")
                        .name("김현욱")
                        .password("1234")
                        .email("fofo@naver.com")
                        .build();
            return userRepository2.save(user).getId();
    }

    // 전체 유저 읽기
    public List<User> read(){
        return userRepository2.findAll();
    }

    public Optional<User> readOne(Long id){
        return userRepository2.findById(id);
    }

    public void deleteAccount(Long id) {
        userRepository2.deleteById(id);
    }






}
