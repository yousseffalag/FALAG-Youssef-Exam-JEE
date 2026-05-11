package falag.youssef.mappers;

import falag.youssef.dtos.PaiementDTO;
import falag.youssef.entities.ContratAssurance;
import falag.youssef.entities.Paiement;
import org.springframework.stereotype.Service;

@Service
public class PaiementMapper {

    public PaiementDTO fromPaiement(Paiement paiement) {
        if (paiement == null) return null;

        PaiementDTO dto = new PaiementDTO();
        dto.setId(paiement.getId());
        dto.setDate(paiement.getDate());
        dto.setMontant(paiement.getMontant());
        dto.setType(paiement.getType());

        if (paiement.getContrat() != null) {
            dto.setContratId(paiement.getContrat().getId());
        }

        return dto;
    }

    public Paiement fromPaiementDTO(PaiementDTO dto, ContratAssurance contrat) {
        if (dto == null) return null;

        Paiement paiement = new Paiement();
        paiement.setId(dto.getId());
        paiement.setDate(dto.getDate());
        paiement.setMontant(dto.getMontant());
        paiement.setType(dto.getType());
        paiement.setContrat(contrat);

        return paiement;
    }
}
