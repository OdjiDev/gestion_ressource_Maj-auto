package com.odji.spring_back_end.facture.entity;

import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "lignefacture",
        indexes = {
                @Index(name = "idx_lignefacture_produit", columnList = "idproduit"),
                @Index(name = "idx_lignefacture_facture", columnList = "idfacture")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class LigneFacture extends AuditableEntity {

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
            foreignKey = @ForeignKey(name = "fk_lignefacture_produit")
    )
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idfacture",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_lignefacture_facture")
    )
    private Facture facture;

    // ==================== Champs métier ====================

    @Column(name = "quantite", precision = 15, scale = 3, nullable = false)
    @Builder.Default
    @ToString.Include
    private BigDecimal quantite = BigDecimal.ZERO;

    /**
     * ⚠️ Date de la ligne de facture.
     * Passé de String → LocalDate (voir explication).
     */
    @Column(name = "date")
    @ToString.Include
    private java.time.LocalDate date;
}
