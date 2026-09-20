package com.odji.spring_back_end.facture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "facturereparer",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_facturereparer_code", columnNames = "code")
        },
        indexes = {
                @Index(name = "idx_facturereparer_date", columnList = "date"),
                @Index(name = "idx_facturereparer_societe", columnList = "idsociete")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class FactureReparer extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "code", length = 50, nullable = false)
    @ToString.Include
    private String code;

    /**
     * ⚠️ CHANGEMENT : Instant → LocalDate
     * Si tu veux garder l'heure, utilise LocalDateTime.
     * Voir explication ci-dessous.
     */
    @Column(name = "date")
    @ToString.Include
    private LocalDate date;

    // ==================== Relations sortantes ====================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idsociete",
            foreignKey = @ForeignKey(name = "fk_facturereparer_societe")
    )
    private Societe societe;

    // ==================== Relations inverses ====================

    @JsonIgnore
    @OneToMany(mappedBy = "factureReparer", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneFactureReparer> lignesFactureReparer = new ArrayList<>();
}
