package com.example.testlocal.service;

import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.vo.GccCompiler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class CompilerService {

    public final String CFileName = "test4.c";
    public final String CExeDirectory = "./hi";
    public final String InputFileName = "test.txt";

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

        List b = new  ArrayList<String>();
        b.add("gcc");
        b.add("-o");
        b.add("hi");
        b.add(CFileName);
        compiler.execCommand(b);

        String result="";

        File f = new File(CExeDirectory);
        File f1 = new File(InputFileName);

        if (f.exists()) {
            List<String> a = new ArrayList<String>();
            a.add("/bin/sh");
            a.add("-c");
            a.add("./hi<./test.txt");
            result = compiler.execCommand(a);
        }
         else {
            result = compiler.execCommand(b);
        }

         f1.delete();
         f.delete();
        return result;
    }
}
