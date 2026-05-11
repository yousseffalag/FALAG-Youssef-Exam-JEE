package falag.youssef.services;

import falag.youssef.dtos.ContratDTO;
import falag.youssef.enums.StatutContrat;

import java.time.LocalDate;
import java.util.List;

public interface ContratService {
    ContratDTO getContrat(Long id);
    List<ContratDTO> getAllContrats();
    List<ContratDTO> getContratsByClient(Long clientId);
    List<ContratDTO> getContratsByStatut(StatutContrat statut);
    List<ContratDTO> getContratsByClientAndStatut(Long clientId, StatutContrat statut);
    List<ContratDTO> getContratsByDateSouscription(LocalDate debut, LocalDate fin);
}
