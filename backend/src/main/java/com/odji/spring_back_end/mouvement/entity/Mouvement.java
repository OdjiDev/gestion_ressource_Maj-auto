package com.odji.spring_back_end.mouvement.entity;

import com.odji.spring_back_end.common.audit.AuditableEntity;
import com.odji.spring_back_end.compte.entity.Compte;
import com.odji.spring_back_end.mouvement.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "mouvement",
        indexes = {
                @Index(name = "idx_mouvement_date", columnList = "date_operation"),
                @Index(name = "idx_mouvement_type", columnList = "type"),
                @Index(name = "idx_mouvement_compte", columnList = "compte_id"),
                @Index(name = "idx_mouvement_statut", columnList = "statut")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Mouvement extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "date_operation", nullable = false)
    private LocalDate dateOperation;

    @Column(name = "date_enregistrement", nullable = false)
    @Builder.Default
    private LocalDateTime dateEnregistrement = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private TypeMouvement type;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 20)
    @Builder.Default
    private StatutMouvement statut = StatutMouvement.VALIDE;

    @Column(name = "montant", nullable = false, precision = 15, scale = 2)
    private BigDecimal montant;

    @Column(name = "devise", nullable = false, length = 10)
    @Builder.Default
    private String devise = "FCFA";

    @Column(name = "motif", nullable = false, length = 500)
    private String motif;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie", nullable = false, length = 50)
    private CategorieMouvement categorie;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_paiement", nullable = false, length = 30)
    private ModePaiement modePaiement;

    @Column(name = "reference", length = 100)
    private String reference;

    @Column(name = "justificatif_path", length = 500)
    private String justificatifPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id", nullable = false, foreignKey = @ForeignKey(name = "fk_mouvement_compte"))
    private Compte compte;

    @Column(name = "facture_id")
    private Long factureId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "validated_by", length = 100)
    private String validatedBy;

    @Column(name = "validated_at")
    private LocalDateTime validatedAt;

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}
