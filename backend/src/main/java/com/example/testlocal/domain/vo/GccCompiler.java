package com.example.testlocal.domain.vo;

import org.zeroturnaround.exec.InvalidExitValueException;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String execCommand(List<String> cmd) throws IOException, InterruptedException, TimeoutException {
        String output="";

        boolean success = false;

        try {
            output = new ProcessExecutor().directory(new File("./")).command(cmd)
                    .redirectErrorStream(true)
                    .readOutput(true)
                    .execute()
                    .outputUTF8();
            success = true;

        }
        catch (InvalidExitValueException e) {
            System.out.println("Process exited with " + e.getExitValue());
            output = e.getResult().outputUTF8();
        }

        return output;

      /*  try {
            List<String> list = new ArrayList<String>();
            list.add(cmd);
            list.add("1");

            processBuilder = new ProcessBuilder(list);
            //Process process = Runtime.getRuntime().exec(cmd);
            process = processBuilder.start();

          //Process process = Runtime.getRuntime().exec(cmd);
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
