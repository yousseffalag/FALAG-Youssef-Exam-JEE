package falag.youssef.services;

import falag.youssef.dtos.AssuranceHabitationDTO;
import falag.youssef.enums.StatutContrat;
import falag.youssef.enums.TypeLogement;

import java.util.List;

public interface AssuranceHabitationService {
    AssuranceHabitationDTO getAssuranceHabitation(Long id);
    List<AssuranceHabitationDTO> getAllAssurancesHabitation();
    List<AssuranceHabitationDTO> getAssurancesHabitationByClient(Long clientId);
    List<AssuranceHabitationDTO> getAssurancesHabitationByStatut(StatutContrat statut);
    List<AssuranceHabitationDTO> getAssurancesHabitationByTypeLogement(TypeLogement typeLogement);
    List<AssuranceHabitationDTO> searchAssurancesHabitationByAdresse(String adresse);
    AssuranceHabitationDTO createAssuranceHabitation(AssuranceHabitationDTO assuranceHabitationDTO);
    AssuranceHabitationDTO updateAssuranceHabitation(Long id, AssuranceHabitationDTO assuranceHabitationDTO);
    AssuranceHabitationDTO validerAssuranceHabitation(Long id);
    AssuranceHabitationDTO resilierAssuranceHabitation(Long id);
    void deleteAssuranceHabitation(Long id);
}
