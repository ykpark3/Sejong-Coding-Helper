package com.example.testlocal.service;

import com.example.testlocal.domain.vo.RenderScriptProcessor;
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
    public final String PythonExeDirectory = "./test.py";
    public final String InputFileName = "test.txt";
    public final String InputFileDirectory = "./test.txt";
    public final String PythonFileName = "test.py";

    @SneakyThrows
    public String sendGcc(String code, String input) {
        createInputFile(input);
        createFile(code, CFileName);
        return executeCompiler("C");
    }

    public void createFile(String code, String fileName){
        try {
            OutputStream output = new FileOutputStream(fileName);
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

    public String executeCompiler(String type) throws IOException, InterruptedException, TimeoutException {
        RenderScriptProcessor compiler = new RenderScriptProcessor();

        switch (type){
            case "C":
                return executeGccCompiler(compiler);
            case "Python":
                return executePythonCompiler(compiler);
            default:
                return "error";
        }
    }

    @SneakyThrows
    public String executeGccCompiler(RenderScriptProcessor compiler){
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

    @SneakyThrows
    public String executePythonCompiler(RenderScriptProcessor compiler){

        File exePythonFile = new File(PythonExeDirectory);
        File textPythonFile = new File(InputFileName);
        String result;

        List<String> executeCommendCFile = new ArrayList<String>();
        executeCommendCFile.add("/bin/sh");
        executeCommendCFile.add("-c");
        executeCommendCFile.add("python3 "+PythonFileName+"<"+InputFileDirectory);
        result = compiler.execCommand(executeCommendCFile);

        textPythonFile.delete();
        exePythonFile.delete();
        return result;
    }


    @SneakyThrows
    public String sendPython(String code, String input) {
        createInputFile(input);
        createFile(code, PythonFileName);
        return executeCompiler("Python");
    }

}
