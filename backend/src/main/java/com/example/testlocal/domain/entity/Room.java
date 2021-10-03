package com.example.testlocal.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "room")
@Table(name = "room")
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Room id

    @Column(name = "assistant_id", nullable = false)
    private Long assistantId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "update_date", nullable = false)
    private String updateDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
