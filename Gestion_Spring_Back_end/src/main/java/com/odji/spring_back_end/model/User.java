package com.odji.spring_back_end.model;

import com.odji.spring_back_end.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_userid", columnNames = "user_id")
        },
        indexes = {
                @Index(name = "idx_users_userid", columnList = "user_id")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true, length = 150)
    @ToString.Include
    private String userId;

    @Column(name = "password", nullable = false, length = 100)
    @ToString.Exclude
    private String password;

    /**
     * ⚠️ À migrer vers un enum ou une relation.
     * String = dangereux (typos, valeurs libres).
     */
    @Column(name = "user_role", nullable = false, length = 50)
    private String userRole;

    // OU mieux : relation vers Option
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "idrole")
    // private Option role;
}
