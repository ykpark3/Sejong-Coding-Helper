package com.example.testlocal.domain.vo;

import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GccCompiler {
    private StringBuffer buffer;
    private Process process;
    private ProcessBuilder processBuilder;
    private BufferedReader bufferedReader;
    private BufferedReader errorBufferedReader;
    private StringBuffer readBuffer;
    private StringBuffer errorReadBuffer;
    private ProcessResult processResult;

    public String execCommand(String[] cmd) throws IOException, InterruptedException, TimeoutException {

        String output = new ProcessExecutor().command(cmd).timeout(10, TimeUnit.SECONDS)
                .readOutput(true).execute()
                .outputUTF8();

        return output;

      /*  try {
            List<String> list = new ArrayList<String>();
            list.add(cmd);
            list.add("1");

            processBuilder = new ProcessBuilder(list);
            //process = Runtime.getRuntime().exec(nargs2);
            process = processBuilder.start();

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
    }*/
    }
}
