package com.odji.spring_back_end.model;

import com.odji.spring_back_end.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "lignereparation",
        indexes = {
                @Index(name = "idx_lignereparation_produit", columnList = "idproduit"),
                @Index(name = "idx_lignereparation_reparer", columnList = "idreparer")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class LigneReparation extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    // ==================== Relations sortantes ====================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idproduit",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_lignereparation_produit")
    )
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idreparer",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_lignereparation_reparer")
    )
    private Reparer reparer;

    // ==================== Champs métier ====================

    @Column(name = "quantite", precision = 15, scale = 3, nullable = false)
    @Builder.Default
    @ToString.Include
    private BigDecimal quantite = BigDecimal.ZERO;

    @Column(name = "date")
    @ToString.Include
    private LocalDate date;
}
