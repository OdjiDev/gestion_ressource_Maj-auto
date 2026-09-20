package com.odji.spring_back_end.user.entity;

import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import com.odji.spring_back_end.option.entity.Option;

@Entity
@Table(
        name = "personel",
        indexes = {
                @Index(name = "idx_personel_nom", columnList = "nom"),
                @Index(name = "idx_personel_email", columnList = "email"),
                @Index(name = "idx_personel_role", columnList = "idrole")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Personel extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "nom", nullable = false, length = 100)
    @ToString.Include
    private String nom;

    @Column(name = "prenom", length = 100)
    @ToString.Include
    private String prenom;

    /**
     * ⚠️ CORRECTION : String → LocalDate
     * Une date de naissance en String = tri faux, format incohérent.
     */
    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;

    @Column(name = "lieu_de_naissance", length = 150)
    private String lieuDeNaissance;

    @Column(name = "sexe", length = 10)
    private String sexe;

    @Column(name = "numero", length = 30)
    private String numero;

    /**
     * ⚠️ Email utilisé pour le login → DOIT être unique.
     */
    @Column(name = "email", nullable = false, unique = true, length = 150)
    @ToString.Include
    private String email;

    /**
     * ⚠️🔴 ATTENTION SÉCURITÉ :
     * Le mot de passe doit TOUJOURS être hashé (BCrypt) AVANT d'être stocké.
     * NE JAMAIS stocker en clair.
     * Géré par Spring Security (AuthService).
     */
    @Column(name = "password", nullable = false, length = 100)
    @ToString.Exclude   // ⚠️ NE JAMAIS logger le password
    private String password;

    // ==================== Relation sortante ====================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idrole",
            foreignKey = @ForeignKey(name = "fk_personel_role")
    )
    private Option role;
}
