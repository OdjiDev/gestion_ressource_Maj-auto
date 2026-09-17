package com.odji.spring_back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "role",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_role_nom", columnNames = "nom")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Option extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    /**
     * Nom du rôle : ADMIN, GESTIONNAIRE, USER, ...
     * DOIT être unique.
     */
    @Column(name = "nom", nullable = false, unique = true, length = 50)
    @ToString.Include
    private String nom;

    // ==================== Relations inverses ====================

    /**
     * ⚠️⚠️ ATTENTION — INCOHÉRENCE À CORRIGER ⚠️⚠️
     *
     * Ton entité Personel a :
     *     @ManyToOne
     *     @JoinColumn(name = "idrole")
     *     private Option role;
     *
     * → le champ s'appelle "role", pas "role" (dans Personel)
     *   ... donc le mappedBy DOIT être "role" (le nom EXACT du champ)
     *
     * Si ton Personel a "private Option role;" alors OK.
     * Si ton Personel a "private Option option;" alors mappedBy = "option".
     */
    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Personel> personels = new ArrayList<>();
}
