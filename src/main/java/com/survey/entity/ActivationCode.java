package com.survey.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "activation_code", schema = "public")
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "code", nullable = false)
    private String code;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public ActivationCode() {
    }

    public ActivationCode(User user) {
        this.code = UUID.randomUUID().toString();
        this.user = user;
        this.creationDate = LocalDateTime.now();
    }

}
