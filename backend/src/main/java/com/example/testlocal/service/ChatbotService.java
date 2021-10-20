package com.example.testlocal.service;

import com.example.testlocal.domain.dto.ChatDTO2;
import com.example.testlocal.domain.dto.ChatbotDTO;
import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.entity.Chatbot;
import com.example.testlocal.domain.vo.GccCompiler;
import com.example.testlocal.exception.InvalidChatIdException;
import com.example.testlocal.repository.ChatRepository;
import com.example.testlocal.repository.ChatbotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatbotService {
    private final ChatbotRepository repository;
    private final UserService2 userService;
    private final ChatbotRoomService chatbotRoomService;

    public Chatbot create(ChatbotDTO chatbotDTO){
        Chatbot chat = new Chatbot(chatbotDTO, userService, chatbotRoomService);
        return repository.save(chat);
    }

    public List<Chatbot> findByRoomId(Long id) {
        return repository.findAllByRoomId(id);
    }

    public String sendGcc(){

        try {
            OutputStream output = new FileOutputStream("./test4.c");
            String str = "#include<stdio.h>\n" + "int main()" + '{' + "printf(\"1244\")" + ';' + '}';
            byte[] by = str.getBytes();
            System.out.printf(str.getBytes().toString());
            output.write(by);
        } catch (Exception e) {
            e.getStackTrace();
        }

        GccCompiler compiler = new GccCompiler();

        String result = compiler.execCommand("gcc test4.c");

        File f = new File("./a.out");

        if (f.exists()) {
            result = compiler.execCommand("./a.out");
            f.delete();
        } else {
            result = compiler.execCommand("gcc test4.c");
        }

        return result;
    }
}
