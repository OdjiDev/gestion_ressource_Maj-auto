package com.odji.spring_back_end.model;

import com.odji.spring_back_end.dto.ProduitDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@Builder
@Table(name = "produit")
@AllArgsConstructor
@NoArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codeproduit")
    private String codeproduit;

    @Column(name = "nom")
    private String nom;

    @Column(name = "designation")
    private String designation;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idcategorie")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "idmagasin")
    private Magasin magasin;

    @OneToMany(mappedBy = "produit")
    private List<Avarie> avarie;

    @OneToMany(mappedBy = "produit")
    private List<LigneFacture> lignefacture;

    @OneToMany(mappedBy = "produit")
    private List<LigneFactureReparer> lignefacturereparer;

    @OneToMany(mappedBy = "produit")
    private List<LigneReparation> lignereparation;

    @OneToMany(mappedBy = "produit")
    private List<LigneDemande> lignedemande;


}
