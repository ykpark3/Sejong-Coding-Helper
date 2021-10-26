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

    public User create(UserDTO2 requestDTO) {
        User user = new User(requestDTO);
        return userRepository2.save(user);
    }

    // 전체 유저 읽기
    public List<User> read() {
        return userRepository2.findAll();
    }

    public Optional<User> readOne(Long id) {
        return userRepository2.findById(id);
    }

    public void deleteAccount(Long id) {
        userRepository2.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository2.findById(id).orElseThrow(() -> new InvalidUserIdException());
    }

    @Transactional
    public Long signUp(UserDTO2 user) {
        // pw를 암호화하는 과정
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        long id = userRepository2.save(user.toEntity()).getId();
        return id;
    }

    // id 중복체크
    public boolean isOverlapStudentNumber(String studentNumber) {
        Optional<User> member = userRepository2.findByStudentNumber(studentNumber);
        if (member.isPresent()) {
            return true;
        }
        return false;
    }

    // email 중복체크
    public boolean isOverlapEmail(String email) {
        Optional<User> member = userRepository2.findByEmail(email);

        if (member.isPresent()) {
            return true;
        }
        return false;
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

    public Map<String, String> refreshToken(String refreshToken) {
        String accessToken = "";

        String username = jwtTokenProvider.getUserPk(refreshToken);

        if (jwtTokenProvider.validateToken(refreshToken)) {
            accessToken = jwtTokenProvider.createToken(username, 60L);
            refreshToken = jwtTokenProvider.createToken(username, 120L);
        } else {
            throw new IllegalArgumentException("토큰 오류");
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