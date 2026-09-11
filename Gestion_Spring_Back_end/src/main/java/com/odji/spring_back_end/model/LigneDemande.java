package com.odji.spring_back_end.model;

import com.odji.spring_back_end.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "lignedemande",
        indexes = {
                @Index(name = "idx_lignedemande_produit", columnList = "idproduit"),
                @Index(name = "idx_lignedemande_demande", columnList = "iddemande"),
                @Index(name = "idx_lignedemande_date", columnList = "date")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class LigneDemande extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    // ==================== Champs métier ====================

    @Column(name = "quantite", precision = 15, scale = 3, nullable = false)
    @Builder.Default
    @ToString.Include
    private BigDecimal quantite = BigDecimal.ZERO;

    @Column(name = "date")
    @ToString.Include
    private LocalDate date;

    // ==================== Relations sortantes ====================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idproduit",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_lignedemande_produit")
    )
    private Produit produit;

    /**
     * ⚠️ CORRECTION MAJEURE : relation SORTANTE vers Demande (singulier)
     * au lieu de la fausse relation inverse List<Demande>.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "iddemande",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_lignedemande_demande")
    )
    private Demande demande;
}
