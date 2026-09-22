package com.odji.spring_back_end.mouvement.repository;

import com.odji.spring_back_end.mouvement.entity.Mouvement;
import com.odji.spring_back_end.mouvement.enums.TypeMouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MouvementRepository extends JpaRepository<Mouvement, Long> {

    @Query("SELECT m FROM Mouvement m JOIN FETCH m.compte WHERE m.deleted = false ORDER BY m.dateOperation DESC, m.id DESC")
    List<Mouvement> findAllActive();

    @Query("SELECT m FROM Mouvement m JOIN FETCH m.compte WHERE m.compte.id = :compteId AND m.deleted = false ORDER BY m.dateOperation DESC")
    List<Mouvement> findByCompteId(@Param("compteId") Long compteId);

    @Query("SELECT COALESCE(SUM(m.montant), 0) FROM Mouvement m WHERE m.compte.id = :compteId AND m.type = :type AND m.statut = 'VALIDE' AND m.deleted = false")
    BigDecimal sumByCompteAndType(@Param("compteId") Long compteId, @Param("type") TypeMouvement type);

    @Query("SELECT COALESCE(SUM(m.montant), 0) FROM Mouvement m WHERE m.type = :type AND m.statut = 'VALIDE' AND m.deleted = false")
    BigDecimal sumByType(@Param("type") TypeMouvement type);
}
