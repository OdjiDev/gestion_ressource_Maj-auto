package com.odji.spring_back_end.fournisseur.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import com.odji.spring_back_end.facture.entity.Facture;

@Entity
@Table(
        name = "fournisseur",
        indexes = {
                @Index(name = "idx_fournisseur_nom", columnList = "nom"),
                @Index(name = "idx_fournisseur_mail", columnList = "mail")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Fournisseur extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "nom", nullable = false, length = 150)
    @ToString.Include
    private String nom;

    @Column(name = "prenom", length = 150)
    private String prenom;

    @Column(name = "adresse", length = 255)
    private String adresse;

    @Column(name = "mail", length = 150)
    @ToString.Include
    private String mail;

    @Column(name = "numtel", length = 30)
    private String numtel;

    // ==================== Relations inverses ====================

    @JsonIgnore
    @OneToMany(mappedBy = "fournisseur", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Facture> factures = new ArrayList<>();
}
