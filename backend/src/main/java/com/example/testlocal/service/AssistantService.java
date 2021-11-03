package com.example.testlocal.service;

import com.example.testlocal.domain.dto.AssistantDTO;
import com.example.testlocal.domain.dto.RoomDTO;
import com.example.testlocal.domain.entity.Assistant;
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
    final private RoomService roomService;
    private final JwtTokenProvider jwtTokenProvider;

    public Assistant create(AssistantDTO requestDTO){
        Assistant assistant = new Assistant(requestDTO, userService);
        return assistantRepository.save(assistant);
    }

    public List<Assistant> read(){
        return assistantRepository.findAll();
    }

    public List<String> findAllByStudentId(String token) {
        String studentNumber = jwtTokenProvider.getUserPk(token);
        return assistantRepository.findAllStudentEmailByStudentNumber(studentNumber);
    }
    public void deleteAssistant(Long id) {
        assistantRepository.deleteById(id);
    }

    public void insertStudentNumbers(String assistantNumber, List<String> studentNumbers) {

        Long id = (long) userService.findUserIdByStudentNumber(assistantNumber);

        for (String number:studentNumbers){
            if(!validateDuplicateAssistant(id, number))
            {
                assistantRepository.save(new Assistant(new AssistantDTO(id, number), userService));

                Long id2 = (long) validateExistsUserId(number);
                if (id2 != 0){
                    roomService.create(new RoomDTO(id, id2, number,""));
                }
            }
        }


    }

    private Boolean validateDuplicateAssistant(Long id, String number) {

        if(assistantRepository.existsByidNumber(id.intValue(), number) == 1){
            return true;
        } else{
            return false;
        }
    }

    private int validateExistsUserId(String number){
        return userService.findUserIdByStudentNumber(number);
    }
}
