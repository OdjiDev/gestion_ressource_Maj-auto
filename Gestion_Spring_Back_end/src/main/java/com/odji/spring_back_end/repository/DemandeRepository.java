import com.odji.spring_back_end.model.Demande;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer> {
    List<Demande> findAllByBureauId(Integer idBureau);
    Page<Demande> findAllByBureauId(Integer idBureau, Pageable pageable);

    @Query("""
        SELECT d FROM Demande d
        LEFT JOIN FETCH d.bureau
        LEFT JOIN FETCH d.lignesDemande
        WHERE d.id = :id
    """)
    Optional<Demande> findByIdWithRelations(@Param("id") Integer id);

    @Query(
            value = "SELECT d FROM Demande d LEFT JOIN FETCH d.bureau",
            countQuery = "SELECT COUNT(d) FROM Demande d"
    )
    //Page<Demande> findAllWithRelations(Pageable pageable);

    long countByBureauId(Integer idBureau);
}
