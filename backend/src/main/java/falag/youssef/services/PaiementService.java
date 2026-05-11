package falag.youssef.services;

import falag.youssef.dtos.PaiementDTO;
import falag.youssef.enums.TypePaiement;

import java.math.BigDecimal;
import java.util.List;

public interface PaiementService {
    PaiementDTO getPaiement(Long id);
    List<PaiementDTO> getAllPaiements();
    List<PaiementDTO> getPaiementsByContrat(Long contratId);
    List<PaiementDTO> getPaiementsByType(TypePaiement type);
    List<PaiementDTO> getPaiementsByContratAndType(Long contratId, TypePaiement type);
    BigDecimal getTotalByContrat(Long contratId);
    BigDecimal getTotalByClient(Long clientId);
    PaiementDTO createPaiement(PaiementDTO paiementDTO);
    PaiementDTO updatePaiement(Long id, PaiementDTO paiementDTO);
    void deletePaiement(Long id);
}
