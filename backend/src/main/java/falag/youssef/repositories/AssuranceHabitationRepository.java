package falag.youssef.repositories;
import falag.youssef.entities.AssuranceHabitation;
import falag.youssef.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AssuranceHabitationRepository extends JpaRepository<AssuranceHabitation, Long> {

    List<AssuranceHabitation> findByTypeLogement(TypeLogement typeLogement);

    List<AssuranceHabitation> findByAdresseContainingIgnoreCase(String adresse);

    List<AssuranceHabitation> findBySuperficieBetween(BigDecimal min, BigDecimal max);

    List<AssuranceHabitation> findByClientId(Long clientId);

    List<AssuranceHabitation> findByStatut(StatutContrat statut);

    List<AssuranceHabitation> findByTypeLogementAndStatut(TypeLogement typeLogement, StatutContrat statut);
}