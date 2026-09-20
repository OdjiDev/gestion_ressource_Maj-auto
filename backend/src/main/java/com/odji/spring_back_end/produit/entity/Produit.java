
package com.odji.spring_back_end.produit.entity;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import com.odji.spring_back_end.common.audit.AuditableEntity;
        import jakarta.persistence.*;
        import lombok.*;

        import java.math.BigDecimal;
        import java.util.ArrayList;
        import java.util.List;

@Entity
@Table(
        name = "produit",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_produit_codeproduit", columnNames = "codeproduit")
        },
        indexes = {
                @Index(name = "idx_produit_nom", columnList = "nom"),
                @Index(name = "idx_produit_categorie", columnList = "idcategorie"),
                @Index(name = "idx_produit_magasin", columnList = "idmagasin")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Produit extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "codeproduit", nullable = false, length = 50)
    @ToString.Include
    private String codeproduit;

    @Column(name = "nom", nullable = false, length = 150)
    @ToString.Include
    private String nom;

    @Column(name = "designation", length = 500)
    private String designation;

    @Column(name = "quantite", precision = 15, scale = 3, nullable = false)
    @Builder.Default
    private BigDecimal quantite = BigDecimal.ZERO;

    // ==================== Relations sortantes (EAGER interdit, LAZY par défaut) ====================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idcategorie",
            foreignKey = @ForeignKey(name = "fk_produit_categorie")
    )
    private Categorie categorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idmagasin",
            foreignKey = @ForeignKey(name = "fk_produit_magasin")
    )
    private Magasin magasin;

    // ==================== Relations inverses (lecture seule, pas de cascade) ====================

    @JsonIgnore
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Avarie> avaries = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneFacture> lignesFacture = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneFactureReparer> lignesFactureReparer = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneReparation> lignesReparation = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneDemande> lignesDemande = new ArrayList<>();

    // ==================== Versioning optimiste (anti-conflit en prod) ====================

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
