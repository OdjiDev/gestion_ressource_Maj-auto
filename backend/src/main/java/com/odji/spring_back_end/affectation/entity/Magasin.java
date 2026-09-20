package com.odji.spring_back_end.affectation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "magasin",
        indexes = {
                @Index(name = "idx_magasin_nom", columnList = "nom")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Magasin extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "nom", nullable = false, length = 150)
    @ToString.Include
    private String nom;

    // ==================== Relations inverses ====================

    @JsonIgnore
    @OneToMany(mappedBy = "magasin", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Produit> produits = new ArrayList<>();
}
