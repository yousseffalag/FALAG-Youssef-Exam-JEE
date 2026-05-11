package falag.youssef.services;

import falag.youssef.dtos.AssuranceSanteDTO;
import falag.youssef.enums.NiveauCouverture;
import falag.youssef.enums.StatutContrat;

import java.util.List;

public interface AssuranceSanteService {
    AssuranceSanteDTO getAssuranceSante(Long id);
    List<AssuranceSanteDTO> getAllAssurancesSante();
    List<AssuranceSanteDTO> getAssurancesSanteByClient(Long clientId);
    List<AssuranceSanteDTO> getAssurancesSanteByStatut(StatutContrat statut);
    List<AssuranceSanteDTO> getAssurancesSanteByNiveauCouverture(NiveauCouverture niveauCouverture);
    List<AssuranceSanteDTO> getAssurancesSanteByMinPersonnes(Integer minPersonnes);
    AssuranceSanteDTO createAssuranceSante(AssuranceSanteDTO assuranceSanteDTO);
    AssuranceSanteDTO updateAssuranceSante(Long id, AssuranceSanteDTO assuranceSanteDTO);
    AssuranceSanteDTO validerAssuranceSante(Long id);
    AssuranceSanteDTO resilierAssuranceSante(Long id);
    void deleteAssuranceSante(Long id);
}
