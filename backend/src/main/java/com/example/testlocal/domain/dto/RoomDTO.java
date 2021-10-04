package com.example.testlocal.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {

    private Long userId;

    private Long assistantId;

    private String title;

    private String updateDate;
}
