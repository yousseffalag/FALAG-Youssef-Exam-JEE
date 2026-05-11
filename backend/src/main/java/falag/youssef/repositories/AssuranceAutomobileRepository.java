package falag.youssef.repositories;
import falag.youssef.entities.AssuranceAutomobile;
import falag.youssef.enums.StatutContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssuranceAutomobileRepository extends JpaRepository<AssuranceAutomobile, Long> {

    Optional<AssuranceAutomobile> findByNumImmatriculation(String numImmatriculation);

    boolean existsByNumImmatriculation(String numImmatriculation);

    List<AssuranceAutomobile> findByMarqueIgnoreCase(String marque);

    List<AssuranceAutomobile> findByMarqueIgnoreCaseAndModeleIgnoreCase(String marque, String modele);

    List<AssuranceAutomobile> findByClientId(Long clientId);

    List<AssuranceAutomobile> findByStatut(StatutContrat statut);
}