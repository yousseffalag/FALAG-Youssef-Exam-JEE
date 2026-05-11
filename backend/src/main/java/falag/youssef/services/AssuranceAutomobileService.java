package falag.youssef.services;

import falag.youssef.dtos.AssuranceAutoDTO;
import falag.youssef.enums.StatutContrat;

import java.util.List;

public interface AssuranceAutomobileService {
    AssuranceAutoDTO getAssuranceAutomobile(Long id);
    List<AssuranceAutoDTO> getAllAssurancesAutomobile();
    List<AssuranceAutoDTO> getAssurancesAutomobileByClient(Long clientId);
    List<AssuranceAutoDTO> getAssurancesAutomobileByStatut(StatutContrat statut);
    List<AssuranceAutoDTO> getAssurancesAutomobileByMarque(String marque);
    AssuranceAutoDTO createAssuranceAutomobile(AssuranceAutoDTO assuranceAutoDTO);
    AssuranceAutoDTO updateAssuranceAutomobile(Long id, AssuranceAutoDTO assuranceAutoDTO);
    AssuranceAutoDTO validerAssuranceAutomobile(Long id);
    AssuranceAutoDTO resilierAssuranceAutomobile(Long id);
    void deleteAssuranceAutomobile(Long id);
}
