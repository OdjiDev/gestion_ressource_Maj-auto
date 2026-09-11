package com.odji.spring_back_end.model;

import com.odji.spring_back_end.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "signaler",
        indexes = {
                @Index(name = "idx_signaler_produit", columnList = "idproduit"),
                @Index(name = "idx_signaler_personel", columnList = "idpersonel"),
                @Index(name = "idx_signaler_etat", columnList = "etat")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Signaler extends AuditableEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
   @ToString.Include
   private Integer id;

   @Column(name = "etat", nullable = false, length = 50)
   @ToString.Include
   private String etat;

   // ==================== Relations sortantes ====================

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(
           name = "idproduit",
           nullable = false,
           foreignKey = @ForeignKey(name = "fk_signaler_produit")
   )
   private Produit produit;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(
           name = "idpersonel",
           nullable = false,
           foreignKey = @ForeignKey(name = "fk_signaler_personel")
   )
   private Personel personel;
}
