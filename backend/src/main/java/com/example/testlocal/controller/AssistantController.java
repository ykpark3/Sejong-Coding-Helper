package com.example.testlocal.controller;

import com.example.testlocal.domain.entity.User;
import com.example.testlocal.service.AssistantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AssistantController {

    final private AssistantService assistantService;
    // 유저 조회
    @GetMapping("/assistant")
    public List<User> all() {
        return assistantService.read();
    }

    // 유저 생성
    @PostMapping("/assistant")
    public String hello(@RequestBody Map<String, String> map) {
        assistantService.create();
        return "good";
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return assistantService.readOne(id);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        assistantService.deleteAccount(id);
        return "delete User" + id.toString();
    }
}
