package com.odji.spring_back_end.avarie.entity;

import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "avarie",
        indexes = {
                @Index(name = "idx_avarie_produit", columnList = "idproduit"),
                @Index(name = "idx_avarie_date", columnList = "date")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Avarie extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "quantite", precision = 15, scale = 3, nullable = false)
    @Builder.Default
    private BigDecimal quantite = BigDecimal.ZERO;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "motif", length = 500)
    private String motif;

    // ==================== Relations sortantes ====================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "idproduit",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_avarie_produit")
    )
    private Produit produit;
}
