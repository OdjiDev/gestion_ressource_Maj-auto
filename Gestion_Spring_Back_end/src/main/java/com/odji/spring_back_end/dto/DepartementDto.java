package com.odji.spring_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartementDto {

    private Integer id;
    private String nom;
    private String code;

    // ❌ Pas de "bureauDto" → relation inverse, endpoint dédié :
    //    GET /api/departements/{id}/bureaux

    // ❌ Pas de "createdAt" → ce champ est technique, pas métier.
    //    Il est géré automatiquement par AuditableEntity côté entité.
}
