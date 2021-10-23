package com.example.testlocal.service;

import com.example.testlocal.domain.dto.AssistantDTO;
import com.example.testlocal.domain.entity.Assistant;
import com.example.testlocal.exception.InvalidAssistantIdException;
import com.example.testlocal.repository.AssistantRepository;
import com.example.testlocal.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssistantService {

    final private AssistantRepository assistantRepository;
    final private UserService2 userService;
    private final JwtTokenProvider jwtTokenProvider;

    public Assistant create(AssistantDTO requestDTO){
        Assistant assistant = new Assistant(requestDTO, userService);
        return assistantRepository.save(assistant);
    }

    public List<Assistant> read(){
        return assistantRepository.findAll();
    }

    public Assistant findById(Long id) {
        return assistantRepository.findById(id).orElseThrow(() -> new InvalidAssistantIdException());
    }

    public List<String> findAllByStudentId(String token) {
        String studentNumber = jwtTokenProvider.getUserPk(token);
        return assistantRepository.findAllStudentEmailByStudentNumber(studentNumber);
    }
    public void deleteAssistant(Long id) {
        assistantRepository.deleteById(id);
    }

}
