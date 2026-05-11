package falag.youssef.repositories;

import falag.youssef.entities.ContratAssurance;
import falag.youssef.enums.StatutContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {

    List<ContratAssurance> findByClientId(Long clientId);

    List<ContratAssurance> findByStatut(StatutContrat statut);

    List<ContratAssurance> findByClientIdAndStatut(Long clientId, StatutContrat statut);

    List<ContratAssurance> findByDateSouscriptionBetween(LocalDate debut, LocalDate fin);

    @Query("SELECT c FROM ContratAssurance c LEFT JOIN FETCH c.paiements WHERE c.id = :id")
    Optional<ContratAssurance> findByIdWithPaiements(Long id);

    @Query("SELECT c FROM ContratAssurance c LEFT JOIN FETCH c.paiements WHERE c.client.id = :clientId")
    List<ContratAssurance> findByClientIdWithPaiements(Long clientId);
}