package com.odji.spring_back_end.categorie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "categorie",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_categorie_code", columnNames = "code")
        },
        indexes = {
                @Index(name = "idx_categorie_nom", columnList = "categorie")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Categorie extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    /** ⚠️ Le nom Java est "nom" mais la colonne BDD est "categorie" (ancienne convention). */
    @Column(name = "categorie", nullable = false, length = 150)
    @ToString.Include
    private String nom;

    @Column(name = "code", nullable = false, length = 50)
    @ToString.Include
    private String code;

    @Column(name = "designation", length = 500)
    private String designation;

    // ==================== Relations inverses ====================

    @JsonIgnore
    @OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Produit> produits = new ArrayList<>();
}
