package com.example.testlocal.service;

import com.example.testlocal.domain.entity.Chat;
import com.example.testlocal.domain.vo.GccCompiler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompilerService {

    public String sendGcc(String code){

        try {
            OutputStream output = new FileOutputStream("./test4.c");
            byte[] bytes = code.getBytes();
            output.write(bytes);
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
