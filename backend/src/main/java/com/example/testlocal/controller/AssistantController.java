package com.example.testlocal.controller;

import com.example.testlocal.domain.dto.AssistantDTO;
import com.example.testlocal.domain.entity.Assistant;
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
    public List<Assistant> all() {
        return assistantService.read();
    }

    @PostMapping("/assistant/studentEmail/{studentNumber}")
    public List<String> findAllStudentEmailByStudentNumber(@PathVariable String studentNumber){return assistantService.findAllByStudentId(studentNumber);}

    // 유저 생성
    @ResponseBody
    @PostMapping("/assistant")
    public Assistant createAssistant(@RequestBody AssistantDTO requestDTO) {
        return assistantService.create(requestDTO);
    }

    @GetMapping("/assistant/{id}")
    public Assistant getAssistant(@PathVariable Long id) {
        return assistantService.findById(id);
    }

    @DeleteMapping("/assistant/{id}")
    public String deleteUser(@PathVariable Long id) {
        assistantService.deleteAssistant(id);
        return "delete Assistant" + id.toString();
    }
}
