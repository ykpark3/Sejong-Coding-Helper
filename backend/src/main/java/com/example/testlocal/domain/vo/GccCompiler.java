package com.example.testlocal.domain.vo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GccCompiler {
    private StringBuffer buffer;
    private Process process;
    private BufferedReader bufferedReader;
    private BufferedReader errorBufferedReader;
    private StringBuffer readBuffer;
    private StringBuffer errorReadBuffer;

    public String execCommand(String cmd) {
        try {
            process = Runtime.getRuntime().exec(cmd);

            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorBufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            String errorLine = null;
            readBuffer = new StringBuffer();
            errorReadBuffer = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null) {
                readBuffer.append(line);
                readBuffer.append("\n");
            }

            while ((errorLine = errorBufferedReader.readLine()) != null) {
                errorReadBuffer.append(errorLine);
                errorReadBuffer.append("\n");
            }

            return readBuffer.toString() + errorReadBuffer.toString();

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }
}
