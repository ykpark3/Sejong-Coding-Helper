package com.example.testlocal.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ChatbotDTO {
    private Long userId;
    private Timestamp createTime;
    private String message;

    public ChatbotDTO(Long userId, String chatMessage) {
        this.userId = userId;
        this.createTime = getCreateTime();
        this.message = chatMessage;
    }

    public Timestamp getCreateTime() {
        createTime = new Timestamp(System.currentTimeMillis());
        return createTime;
    }
}
