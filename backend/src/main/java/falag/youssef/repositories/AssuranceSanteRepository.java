package falag.youssef.repositories;
import falag.youssef.entities.AssuranceSante;
import falag.youssef.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssuranceSanteRepository extends JpaRepository<AssuranceSante, Long> {

    List<AssuranceSante> findByNiveauCouverture(NiveauCouverture niveauCouverture);

    List<AssuranceSante> findByNbPersonnesCouvertesGreaterThanEqual(Integer nb);

    List<AssuranceSante> findByClientId(Long clientId);

    List<AssuranceSante> findByStatut(StatutContrat statut);

    List<AssuranceSante> findByNiveauCouvertureAndStatut(NiveauCouverture niveauCouverture, StatutContrat statut);
}