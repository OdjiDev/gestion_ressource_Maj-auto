package com.odji.spring_back_end.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;
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

<<<<<<< HEAD
    private Instant datecommande;
=======
    private String total;

    private Date datecommande;
>>>>>>> origin/main

    private FournisseurDto fournisseurDto;

    @JsonIgnore
    private List<LigneFactureDto> lignefactures;


}
