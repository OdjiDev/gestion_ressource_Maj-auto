package com.odji.spring_back_end.societe.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "contrat")
@AllArgsConstructor
@NoArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;


    @Column(name = "datedebut")
    private LocalDate datedebut;

    @Column(name = "datedefin")
    private LocalDate datedefin;

    @ManyToOne
    @JoinColumn(name = "idsociete")
    private Societe societe ;
}
