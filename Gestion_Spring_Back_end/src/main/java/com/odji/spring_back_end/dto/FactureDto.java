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

<<<<<<< HEAD
=======
    private String numero;

>>>>>>> 7436a0253caf3d0ac9a608e92cad1da31f5f0547
    private String code;

    private Instant datecommande;

    private FournisseurDto fournisseurDto;

    @JsonIgnore
    private List<LigneFactureDto> lignefactures;


}
