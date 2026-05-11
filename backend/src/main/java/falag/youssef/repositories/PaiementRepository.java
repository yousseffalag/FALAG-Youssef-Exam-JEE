package falag.youssef.repositories;

import falag.youssef.entities.Paiement;
import falag.youssef.enums.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByContratId(Long contratId);

    List<Paiement> findByType(TypePaiement type);

    List<Paiement> findByDateBetween(LocalDate debut, LocalDate fin);

    List<Paiement> findByContratIdAndType(Long contratId, TypePaiement type);

    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.contrat.id = :contratId")
    BigDecimal sumMontantByContratId(Long contratId);

    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.contrat.client.id = :clientId")
    BigDecimal sumMontantByClientId(Long clientId);
}