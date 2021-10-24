package com.example.testlocal.service;

import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.vo.GccCompiler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class CompilerService {

    public String sendGcc(String code) throws IOException, InterruptedException, TimeoutException {

        try {
            OutputStream output = new FileOutputStream("./test4.c");
            byte[] bytes = code.getBytes();
            output.write(bytes);
        } catch (Exception e) {
            e.getStackTrace();
        }

        GccCompiler compiler = new GccCompiler();

        String[] str = new String[2];
        str[0] = "gcc";
        str[1] = "test4.c";
        String result = compiler.execCommand(str);

        File f = new File("./a.out");

        if (f.exists()) {
            String[] str1 = new String[1];
            str1[0] = "./a.out";
            result = compiler.execCommand(str1);
            f.delete();
        } else {
            result = compiler.execCommand(str);
        }

        return result;
    }


}
