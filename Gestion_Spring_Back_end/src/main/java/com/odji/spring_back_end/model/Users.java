package com.odji.spring_back_end.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "loginUsers")
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "password")
    private String password;

        @ManyToOne
        @JoinColumn(name = "idroles")
        private Roles  roles;


    }
