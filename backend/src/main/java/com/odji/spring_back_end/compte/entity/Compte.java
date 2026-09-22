package com.odji.spring_back_end.compte.entity;

import com.odji.spring_back_end.common.audit.AuditableEntity;
import com.odji.spring_back_end.compte.enums.TypeCompte;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "compte",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_compte_code", columnNames = "code")
        },
        indexes = {
                @Index(name = "idx_compte_type", columnList = "type"),
                @Index(name = "idx_compte_actif", columnList = "actif")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Compte extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "code", nullable = false, length = 50)
    @ToString.Include
    private String code;

    @Column(name = "nom", nullable = false, length = 150)
    @ToString.Include
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 30)
    private TypeCompte type;

    @Column(name = "solde_initial", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal soldeInitial = BigDecimal.ZERO;

    @Column(name = "devise", nullable = false, length = 10)
    @Builder.Default
    private String devise = "FCFA";

    @Column(name = "seuil_alerte", precision = 15, scale = 2)
    private BigDecimal seuilAlerte;

    @Column(name = "actif", nullable = false)
    @Builder.Default
    private Boolean actif = true;

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}
