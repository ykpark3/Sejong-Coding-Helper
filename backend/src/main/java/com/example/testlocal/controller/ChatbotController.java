package com.example.testlocal.controller;

import com.example.testlocal.config.ApiKey;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.service.spi.InjectService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.example.testlocal.config.ApiKey.apiUrl;
import static com.example.testlocal.config.ApiKey.secretKey;

@Controller
public class ChatbotController {

    public ChatbotController() {

        System.out.println("ChatbotController!!!!!!!!");
    }

    //private final ApiKey apiKey = ApiKey.getInstance();

    @Autowired
    private ApiKey apiKey;


   // private final String secretKey = apiKey.getSecretKey();
   // private final String apiUrl = apiKey.getApiUrl();


    @MessageMapping("/sendMessage") //client -> server & "/app/sendMessage"
    @SendTo("/topic/public")    //server -> client
    public String sendMessage(@Payload String chatMessage) throws IOException {

        System.out.println("!!!!! sendMessage");

        URL url = new URL(apiUrl);
        String message =  getReqMessage(chatMessage);
        String encodeBase64String = makeSignature(message);

        System.out.println("sendMessage message: "+message);
        //System.out.println("sendMessage encodeBase64String: "+encodeBase64String);


        // api서버 접속
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;UTF-8");
        con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());

        System.out.println("!!! wr: "+wr);

        wr.write(message.getBytes("UTF-8"));
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();

     //   BufferedReader br;

        if(responseCode==200) { // 정상 호출

            System.out.println("정상 호출 !!!!!!!!");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream(), "UTF-8"));
            String decodedString;
            String jsonString = "";
            while ((decodedString = in.readLine()) != null) {
                jsonString = decodedString; //jsonString에 값 받아오기
            }

            System.out.println("!!!!!!!jsonstring:   "+jsonString);

            //받아온 값을 세팅하는 부분
            JSONParser jsonParser = new JSONParser();
            //JSONParser jsonParser = new JSONParser(jsonString);
            try {
                System.out.println("!!!try");

                JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonString);

                // "bubbles": [ {"type": "text",
                //"data" : { "description" : "postback text of welcome action" } } ],
                //"event": "open"
                JSONArray bubblesArray = (JSONArray)jsonObject.get("bubbles");
                JSONObject bubbles = (JSONObject)bubblesArray.get(0);

                JSONObject data = (JSONObject)bubbles.get("data");
                String description = "";
                description = (String)data.get("description");
                chatMessage = description;

                System.out.println("!!!!! chatMessage: " + chatMessage);

            } catch (Exception e) {
                System.out.println("!!!!!error");
                e.printStackTrace();
            }

            in.close();
        }

        else {  // 에러 발생

            System.out.println("!!!!!error 22");
            chatMessage = con.getResponseMessage();
        }
        return chatMessage;
    }

    //보낼 메세지를 네이버에서 제공해준 암호화로 변경해주는 메소드
    public static String makeSignature(String message) {

        System.out.println("!!!!! makeSignature");
        String signatureHeader = "";


        // 요청 Signature 헤더
        try {
            byte[] secrete_key_bytes = secretKey.getBytes(StandardCharsets.UTF_8);
         //   byte[] secrete_key_bytes = secretKey.getBytes("UTF_8");


            SecretKeySpec secretKeySpec = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            System.out.println("makeSignature message: "+message);

            byte[] signature  = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
           // byte[] signature  = mac.doFinal(message.getBytes("UTF-8"));
            System.out.println("signature: "+signature);

            //String signatureHeader = Base64.getEncoder().encodeToString(signature);
            signatureHeader= Base64.encodeBase64String(signature);
            //  signatureHeader = Base64.encodeToString(signature, Base64.NO_WRAP);
            System.out.println("!!!!signitureHeader:  "+signatureHeader);

            return signatureHeader;

        } catch (Exception e){
            System.out.println("!!!!error ");
            System.out.println(e);
        }
        return signatureHeader;
    }

    //보낼 메세지를 네이버 챗봇에 포맷으로 변경해주는 메소드
    public static String getReqMessage(String sendMessage) {

        String requestBody = "";

        System.out.println("!!!!! getReqMessage !!!!!");

        System.out.println("sendMessage: "+sendMessage);

        //sendMessage = "파이썬이 뭐야";

        try {

            JSONObject jsonObject = new JSONObject();

            long timestamp = new Date().getTime();

            //System.out.println("##"+timestamp);

            jsonObject.put("version", "v2");
            // userid는 유일한 키값이면 아무거나 가능
            // 일단 예시 값 넣어두기
            jsonObject.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");  
            jsonObject.put("timestamp", timestamp);

            JSONObject bubbles_obj = new JSONObject();

            //bubbles_obj.put("type", "text");

            JSONObject data_obj = new JSONObject();
            data_obj.put("description", sendMessage);

            bubbles_obj.put("type", "text");
            bubbles_obj.put("data", data_obj);

            JSONArray bubbles_array = new JSONArray();
            bubbles_array.add(bubbles_obj);

            jsonObject.put("bubbles", bubbles_array);
            jsonObject.put("event", "send");

            requestBody = jsonObject.toString();

            System.out.println("!!! requestBody:   "+requestBody);

        } catch (Exception e){
            System.out.println("## Exception : " + e);
        }
        return requestBody;
    }

}
