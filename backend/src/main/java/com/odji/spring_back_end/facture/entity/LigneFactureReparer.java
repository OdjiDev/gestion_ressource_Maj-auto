package com.odji.spring_back_end.facture.entity;

import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "lignefacturereparer",
        indexes = {
                @Index(name = "idx_lignefacturereparer_produit", columnList = "idproduit"),
                @Index(name = "idx_lignefacturereparer_reparer", columnList = "idreparer"),
                @Index(name = "idx_lignefacturereparer_facturereparer", columnList = "idfacturereparer")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class LigneFactureReparer extends AuditableEntity {

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
            foreignKey = @ForeignKey(name = "fk_lignefacturereparer_produit")
    )
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idreparer",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_lignefacturereparer_reparer")
    )
    private Reparer reparer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idfacturereparer",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_lignefacturereparer_facture")
    )
    private FactureReparer factureReparer;

    // ==================== Champs métier ====================

    @Column(name = "quantite", precision = 15, scale = 3, nullable = false)
    @Builder.Default
    @ToString.Include
    private BigDecimal quantite = BigDecimal.ZERO;

    @Column(name = "date")
    @ToString.Include
    private LocalDate date;
}
