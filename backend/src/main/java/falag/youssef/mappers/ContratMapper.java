package falag.youssef.mappers;

import falag.youssef.dtos.AssuranceAutoDTO;
import falag.youssef.dtos.AssuranceHabitationDTO;
import falag.youssef.dtos.AssuranceSanteDTO;
import falag.youssef.dtos.ContratDTO;
import falag.youssef.entities.AssuranceAutomobile;
import falag.youssef.entities.AssuranceHabitation;
import falag.youssef.entities.AssuranceSante;
import falag.youssef.entities.Client;
import falag.youssef.entities.ContratAssurance;
import org.springframework.stereotype.Service;

@Service
public class ContratMapper {

    public ContratDTO fromContrat(ContratAssurance contrat) {
        if (contrat == null) return null;

        if (contrat instanceof AssuranceAutomobile assuranceAutomobile) {
            return fromAssuranceAutomobile(assuranceAutomobile);
        }

        if (contrat instanceof AssuranceHabitation assuranceHabitation) {
            return fromAssuranceHabitation(assuranceHabitation);
        }

        if (contrat instanceof AssuranceSante assuranceSante) {
            return fromAssuranceSante(assuranceSante);
        }

        ContratDTO dto = new ContratDTO();
        fillContratDTO(dto, contrat);

        return dto;
    }

    public AssuranceAutoDTO fromAssuranceAutomobile(AssuranceAutomobile assuranceAutomobile) {
        if (assuranceAutomobile == null) return null;

        AssuranceAutoDTO dto = new AssuranceAutoDTO();
        fillContratDTO(dto, assuranceAutomobile);
        dto.setNumImmatriculation(assuranceAutomobile.getNumImmatriculation());
        dto.setMarque(assuranceAutomobile.getMarque());
        dto.setModele(assuranceAutomobile.getModele());

        return dto;
    }

    public AssuranceAutomobile fromAssuranceAutoDTO(AssuranceAutoDTO dto, Client client) {
        if (dto == null) return null;

        AssuranceAutomobile assuranceAutomobile = new AssuranceAutomobile();
        fillContrat(assuranceAutomobile, dto, client);
        assuranceAutomobile.setNumImmatriculation(dto.getNumImmatriculation());
        assuranceAutomobile.setMarque(dto.getMarque());
        assuranceAutomobile.setModele(dto.getModele());

        return assuranceAutomobile;
    }

    public AssuranceHabitationDTO fromAssuranceHabitation(AssuranceHabitation assuranceHabitation) {
        if (assuranceHabitation == null) return null;

        AssuranceHabitationDTO dto = new AssuranceHabitationDTO();
        fillContratDTO(dto, assuranceHabitation);
        dto.setTypeLogement(assuranceHabitation.getTypeLogement());
        dto.setAdresse(assuranceHabitation.getAdresse());
        dto.setSuperficie(assuranceHabitation.getSuperficie());

        return dto;
    }

    public AssuranceHabitation fromAssuranceHabitationDTO(AssuranceHabitationDTO dto, Client client) {
        if (dto == null) return null;

        AssuranceHabitation assuranceHabitation = new AssuranceHabitation();
        fillContrat(assuranceHabitation, dto, client);
        assuranceHabitation.setTypeLogement(dto.getTypeLogement());
        assuranceHabitation.setAdresse(dto.getAdresse());
        assuranceHabitation.setSuperficie(dto.getSuperficie());

        return assuranceHabitation;
    }

    public AssuranceSanteDTO fromAssuranceSante(AssuranceSante assuranceSante) {
        if (assuranceSante == null) return null;

        AssuranceSanteDTO dto = new AssuranceSanteDTO();
        fillContratDTO(dto, assuranceSante);
        dto.setNiveauCouverture(assuranceSante.getNiveauCouverture());
        dto.setNbPersonnesCouvertes(assuranceSante.getNbPersonnesCouvertes());

        return dto;
    }

    public AssuranceSante fromAssuranceSanteDTO(AssuranceSanteDTO dto, Client client) {
        if (dto == null) return null;

        AssuranceSante assuranceSante = new AssuranceSante();
        fillContrat(assuranceSante, dto, client);
        assuranceSante.setNiveauCouverture(dto.getNiveauCouverture());
        assuranceSante.setNbPersonnesCouvertes(dto.getNbPersonnesCouvertes());

        return assuranceSante;
    }

    private void fillContratDTO(ContratDTO dto, ContratAssurance contrat) {
        dto.setId(contrat.getId());
        dto.setDateSouscription(contrat.getDateSouscription());
        dto.setStatut(contrat.getStatut());
        dto.setDateValidation(contrat.getDateValidation());
        dto.setMontantCotisation(contrat.getMontantCotisation());
        dto.setDureeContrat(contrat.getDureeContrat());
        dto.setTauxCouverture(contrat.getTauxCouverture());

        if (contrat.getClient() != null) {
            dto.setClientId(contrat.getClient().getId());
        }
    }

    private void fillContrat(ContratAssurance contrat, ContratDTO dto, Client client) {
        contrat.setId(dto.getId());
        contrat.setDateSouscription(dto.getDateSouscription());
        contrat.setStatut(dto.getStatut());
        contrat.setDateValidation(dto.getDateValidation());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        contrat.setClient(client);
    }
}
