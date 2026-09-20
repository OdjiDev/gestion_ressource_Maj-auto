package com.odji.spring_back_end.affectation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "bureau",
        indexes = {
                @Index(name = "idx_bureau_nom", columnList = "nom"),
                @Index(name = "idx_bureau_departement", columnList = "iddepartement")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Bureau extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "nom", nullable = false, length = 150)
    @ToString.Include
    private String nom;

    // ==================== Relations sortantes ====================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "iddepartement",
            foreignKey = @ForeignKey(name = "fk_bureau_departement")
    )
    private Departement departement;

    // ==================== Relations inverses ====================

    @JsonIgnore
    @OneToMany(mappedBy = "bureau", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Demande> demandes = new ArrayList<>();
}
