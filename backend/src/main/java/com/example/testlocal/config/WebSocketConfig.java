package com.example.testlocal.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        System.out.println("!!!!!registerStompEndpoints");
        //해당 부분은 웹 소켓이 연결되는 엔드포인트를 “/ws” 로 설정
        //SockJS와 연동해 웹 소켓을 지원하지 않는 브라우저의 경우 SockJS 규격으로 연결
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){

        System.out.println("!!!!!configureMessageBroker");

        // 도착 경로에 대한 prefix 설정
        // /app으로 설정하면 /topic/hello의 토픽에 대해 구독 신청 시
        // 실제 경로는 /app/topic/hello
        registry.setApplicationDestinationPrefixes("/app");

//        한 명이 message를 발행하였을 때
//        해당 토픽을 구독하는 n명에게 메세지를 보내는 경우는 topic
//        한 명에게 메세지를 보내는 경우는 queue
        registry.enableSimpleBroker("/topic");  // topic 브로커를 설정

    }

}
