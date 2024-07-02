package com.odji.spring_back_end.repository;

import com.odji.spring_back_end.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository  extends JpaRepository<Produit, Integer> {

    Optional<Produit> findArticleByCodeproduit(String codeproduit);

   List<Produit> findAllByCategorieId(Integer idCategorie);
}
