package com.example.testlocal.domain.entity;

import com.example.testlocal.domain.dto.AssistantDTO;
import com.example.testlocal.domain.dto.UserDTO2;
import com.example.testlocal.service.UserService2;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Assistant(AssistantDTO requestDTO, UserService2 userService) {
        this.email = requestDTO.getStudentEmail();
        this.user = userService.findById(requestDTO.getUserId());
    }

}
