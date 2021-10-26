package com.example.testlocal.service;

import com.example.testlocal.domain.vo.GccCompiler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class CompilerService {

    public final String CFileName = "test4.c";
    public final String CExeDirectory = "./hi";
    public final String InputFileName = "test.txt";
    public final String InputFileDirectory = "./test.txt";

    @SneakyThrows
    public String sendGcc(String code, String input) {
        createInputFile(input);
        createCFile(code);
        return executeGccCompiler();
    }

    public void createCFile(String code){
        try {
            OutputStream output = new FileOutputStream(CFileName);
            byte[] bytes = code.getBytes();
            output.write(bytes);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void createInputFile(String input){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(InputFileName, true));
            bufferedWriter.write(input);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String executeGccCompiler() throws IOException, InterruptedException, TimeoutException {
        GccCompiler compiler = new GccCompiler();

        List createdList = new ArrayList<String>();
        createdList.add("gcc");
        createdList.add("-o");
        createdList.add("hi");
        createdList.add(CFileName);
        compiler.execCommand(createdList);

        String result="";

        File exeCFile = new File(CExeDirectory);
        File textCFile = new File(InputFileName);

        if (exeCFile.exists()) {
            List<String> executeCommendCFile = new ArrayList<String>();
            executeCommendCFile.add("/bin/sh");
            executeCommendCFile.add("-c");
            executeCommendCFile.add(CExeDirectory+"<"+InputFileDirectory);
            result = compiler.execCommand(executeCommendCFile);
        }
         else {
            result = compiler.execCommand(createdList);
        }

        textCFile.delete();
        exeCFile.delete();
        return result;
    }
}
