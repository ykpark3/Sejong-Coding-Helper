package com.example.testlocal.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ChatDTO2 {
    private Long roomId;
    private Long userId;
    private Timestamp createTime;
    private String name;
    private String message;

    public Timestamp getCreateTime() {
        createTime = new Timestamp(System.currentTimeMillis());
        return createTime;
    }
}
