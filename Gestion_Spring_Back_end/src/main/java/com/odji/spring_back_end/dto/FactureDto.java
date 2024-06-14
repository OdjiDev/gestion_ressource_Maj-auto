package com.odji.spring_back_end.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odji.spring_back_end.model.Facture;
import com.odji.spring_back_end.model.Fournisseur;
import com.odji.spring_back_end.model.LigneFacture;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.Instant;
import java.util.List;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FactureDto {

    private Integer id;

    private String code;

    private Instant datecommande;

    private FournisseurDto fournisseur;

    @JsonIgnore
    private List<LigneFactureDto> lignefactures;


}
