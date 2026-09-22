package com.odji.spring_back_end.mouvement.dto;

import com.odji.spring_back_end.mouvement.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MouvementCreateRequest {

    @NotNull(message = "La date est obligatoire")
    private LocalDate dateOperation;

    @NotNull(message = "Le type est obligatoire")
    private TypeMouvement type;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;

    private String devise;

    @NotBlank(message = "Le motif est obligatoire")
    private String motif;

    @NotNull(message = "La catégorie est obligatoire")
    private CategorieMouvement categorie;

    @NotNull(message = "Le mode de paiement est obligatoire")
    private ModePaiement modePaiement;

    private String reference;

    @NotNull(message = "Le compte est obligatoire")
    private Long compteId;

    private Long factureId;
}
