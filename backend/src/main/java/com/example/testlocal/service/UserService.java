package com.example.testlocal.service;

import com.example.testlocal.domain.dto.UserDTO2;
import com.example.testlocal.domain.entity.User;
import com.example.testlocal.exception.InvalidUserIdException;
import com.example.testlocal.repository.UserRepository2;
import com.example.testlocal.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository2 userRepository2;

    public User create(UserDTO2 requestDTO){
        User user = new User(requestDTO);
        return userRepository2.save(user);
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

    public User findById(Long id) {
        return userRepository2.findById(id).orElseThrow(() -> new InvalidUserIdException());
    }

    @Transactional
    public void signUp(UserDTO2 user) {
        // pw를 암호화하는 과정
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository2.save(user.toEntity());
    }

    // 중복체크
    public void checkId(String id) {
        Optional<User> member = userRepository2.findByStudentNumber(id);
        if (member.isPresent()) {

        } else {
        }
    }

    public Map<String, String> login(Map<String, String> user) {

        String accessToken = "";
        String refreshToken = "";

        // id확인
        User checkedUser = userRepository2.findByStudentNumber(user.get("id"))
                .orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다."));

        // 비번 확인
        if (!passwordEncoder.matches(user.get("pwd"), checkedUser.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀 번호입니다.");
        }

        accessToken = jwtTokenProvider.createToken(checkedUser.getStudentNumber(), 10L);
        refreshToken = jwtTokenProvider.createToken(checkedUser.getStudentNumber(), 60L);

        Map<String, String> map = new HashMap<>();
        map.put("accessToken", accessToken);
        map.put("refreshToken", refreshToken);

        return map;

    }

    public Map<String, String> refreshToken(String id, Cookie[] cookies) {
        String accessToken = "";
        String refreshToken = "";
        System.out.println(cookies);

        if (cookies != null && cookies.length > 0) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();

                    if (jwtTokenProvider.validateToken(refreshToken)) {
                        accessToken = jwtTokenProvider.createToken(id, 60L);
                        refreshToken = jwtTokenProvider.createToken(id, 120L);
                    } else {
                        throw new IllegalArgumentException("토큰 오류");
                    }
                }
            }
        }

        if (refreshToken == null || "".equals(refreshToken)) {
            throw new IllegalArgumentException("토큰 오류");
        }

        Map<String, String> map = new HashMap<>();
        map.put("accessToken", accessToken);
        map.put("refreshToken", refreshToken);

        return map;
    }

}