package com.odji.spring_back_end.model;

import com.odji.spring_back_end.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "affectation",
        indexes = {
                @Index(name = "idx_affectation_produit", columnList = "idproduit"),
                @Index(name = "idx_affectation_personel", columnList = "idpersonel"),
                @Index(name = "idx_affectation_date", columnList = "date")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Affectation extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "quantite", precision = 15, scale = 3, nullable = false)
    @Builder.Default
    @ToString.Include
    private BigDecimal quantite = BigDecimal.ZERO;

    /**
     * ⚠️ CHANGEMENT CLÉ : String → LocalDate
     * C'est CE changement qui corrige l'erreur du service.
     */
    @Column(name = "date")
    @ToString.Include
    private LocalDate date;

    @Column(name = "motif", length = 500)
    private String motif;

    // ==================== Relations sortantes ====================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idproduit",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_affectation_produit")
    )
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idpersonel",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_affectation_personel")
    )
    private Personel personel;
}
