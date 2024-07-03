package com.odji.spring_back_end.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Data
@Builder
@Entity
@Table(name = "facture")
@AllArgsConstructor
@NoArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero", unique = true)
    private String numero;

    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datecommande;

    @Column(name = "total")
    private String total;

    @ManyToOne
    @JoinColumn(name = "idfournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "facture")
    private List<LigneFacture> lignefacture;


}
