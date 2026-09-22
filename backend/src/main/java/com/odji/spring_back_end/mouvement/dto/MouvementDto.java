package com.odji.spring_back_end.mouvement.dto;

import com.odji.spring_back_end.mouvement.enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MouvementDto {
    private Long id;
    private LocalDate dateOperation;
    private LocalDateTime dateEnregistrement;
    private TypeMouvement type;
    private StatutMouvement statut;
    private BigDecimal montant;
    private String devise;
    private String motif;
    private CategorieMouvement categorie;
    private ModePaiement modePaiement;
    private String reference;
    private String justificatifPath;
    private Long compteId;
    private String compteNom;
    private Long factureId;
    private Long parentId;
    private String validatedBy;
    private LocalDateTime validatedAt;
}
