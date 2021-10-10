package com.example.testlocal.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker   // Websocket 서버 사용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    // 클라이언트에서 websocket에 접속하는 endpoint를 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        System.out.println("!!!!!registerStompEndpoints");

        //웹 소켓이 연결되는 엔드포인트를 “/ws” 로 설정
        //SockJS와 연동해 웹 소켓을 지원하지 않는 브라우저의 경우 SockJS 규격으로 연결
        registry.addEndpoint("/ws").withSockJS();
    }

    // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅할 때 사용하는 브로커 구성
    //publish, subscribe 할 때 사용할 url prefix 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        System.out.println("!!!!!configureMessageBroker");

        registry.setApplicationDestinationPrefixes("/app"); // client -> server 메시지 전송 시 endpoint
        registry.enableSimpleBroker("/topic");  // server -> client 메시지 전송 시 endpoint

    }

}
