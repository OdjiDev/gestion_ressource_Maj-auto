package com.odji.spring_back_end.facture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.odji.spring_back_end.fournisseur.entity.Fournisseur;

@Entity
@Table(
        name = "facture",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_facture_numero", columnNames = "numero")
        },
        indexes = {
                @Index(name = "idx_facture_code", columnList = "code"),
                @Index(name = "idx_facture_datecommande", columnList = "datecommande"),
                @Index(name = "idx_facture_fournisseur", columnList = "idfournisseur")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Facture extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "numero", length = 50, nullable = false)
    @ToString.Include
    private String numero;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "datecommande")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ToString.Include
    private LocalDate datecommande;

    // ==================== Relations sortantes ====================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idfournisseur",
            foreignKey = @ForeignKey(name = "fk_facture_fournisseur")
    )
    private Fournisseur fournisseur;

    // ==================== Relations inverses ====================

    @JsonIgnore
    @OneToMany(mappedBy = "facture", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneFacture> lignesFacture = new ArrayList<>();
}
