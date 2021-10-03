package com.example.testlocal.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "assistant")
@Table(name = "assistant")
@Builder
public class Assistant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 조교 id

    @Column(name = "student_email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Assistant(String student_email, Long userId) {
        this.user = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

}
