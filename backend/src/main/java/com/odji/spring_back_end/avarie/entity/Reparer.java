package com.odji.spring_back_end.avarie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "reparer",
        indexes = {
                @Index(name = "idx_reparer_date", columnList = "date")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class Reparer extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    /**
     * ⚠️ CHANGEMENT CLÉ : Date → LocalDate
     */
    @Column(name = "date")
    @ToString.Include
    private LocalDate date;

    @Column(name = "motif", length = 500)
    private String motif;

    // ==================== Relations inverses ====================

    @JsonIgnore
    @OneToMany(mappedBy = "reparer", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneReparation> lignesReparation = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "reparer", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LigneFactureReparer> lignesFactureReparer = new ArrayList<>();
}
