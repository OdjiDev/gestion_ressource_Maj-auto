package com.odji.spring_back_end.demande.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import com.odji.spring_back_end.affectation.entity.Bureau;

@Entity
@Table(
        name = "demande",
        indexes = {
                @Index(name = "idx_demande_bureau", columnList = "idbureau")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Demande extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "motif", length = 500)
    @ToString.Include
    private String motif;

    // ==================== Relation sortante ====================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idbureau",
            foreignKey = @ForeignKey(name = "fk_demande_bureau")
    )
    private Bureau bureau;

    // ==================== Relation inverse (le VRAI modèle) ====================

    @JsonIgnore
    @OneToMany(mappedBy = "demande", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneDemande> lignesDemande = new ArrayList<>();
}
