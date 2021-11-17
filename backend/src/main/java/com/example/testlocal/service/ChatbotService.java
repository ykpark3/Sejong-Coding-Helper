package com.example.testlocal.service;

import com.example.testlocal.domain.dto.ChatbotDTO;
import com.example.testlocal.domain.entity.Chatbot;
import com.example.testlocal.domain.vo.RenderScriptProcessor;
import com.example.testlocal.repository.ChatbotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

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

    public String executePython(String input) throws IOException, InterruptedException, TimeoutException {

        RenderScriptProcessor compiler = new RenderScriptProcessor();
        String PythonExeDirectory =  "./chatbotmodel-sejong-test/test/chatbot_test.py";
        String InputPythonFileDirectory = "./chatbotmodel-sejong-test/test/text.txt";

            try{
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./chatbotmodel-sejong-test/test/text.txt", true));
                bufferedWriter.write(input);
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception e){
                e.printStackTrace();
            }

        File exePythonFile = new File("./chatbotmodel-sejong-test/test/chatbot_test.py");
        File textPythonFile = new File("./chatbotmodel-sejong-test/test/text.txt");
        String result;

        List<String> executeCommendCFile = new ArrayList<String>();
        executeCommendCFile.add("/bin/sh");
        executeCommendCFile.add("-c");
        executeCommendCFile.add("python3 "+PythonExeDirectory+"<"+InputPythonFileDirectory);
        result = compiler.execCommand(executeCommendCFile);

//conda activate tensorflow

        textPythonFile.delete();
        return  result;

    }

}