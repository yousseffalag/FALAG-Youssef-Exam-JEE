package falag.youssef.services;

import falag.youssef.dtos.AssuranceHabitationDTO;
import falag.youssef.entities.AssuranceHabitation;
import falag.youssef.entities.Client;
import falag.youssef.enums.StatutContrat;
import falag.youssef.enums.TypeLogement;
import falag.youssef.mappers.ContratMapper;
import falag.youssef.repositories.AssuranceHabitationRepository;
import falag.youssef.repositories.ClientRepository;
import falag.youssef.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AssuranceHabitationServiceImpl implements AssuranceHabitationService {

    private AssuranceHabitationRepository habitationRepository;
    private ClientRepository clientRepository;
    private ContratMapper contratMapper;

    @Override
    public AssuranceHabitationDTO getAssuranceHabitation(Long id) {
        return contratMapper.fromAssuranceHabitation(findAssurance(id));
    }

    @Override
    public List<AssuranceHabitationDTO> getAllAssurancesHabitation() {
        return habitationRepository.findAll().stream()
                .map(contratMapper::fromAssuranceHabitation)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceHabitationDTO> getAssurancesHabitationByClient(Long clientId) {
        return habitationRepository.findByClientId(clientId).stream()
                .map(contratMapper::fromAssuranceHabitation)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceHabitationDTO> getAssurancesHabitationByStatut(StatutContrat statut) {
        return habitationRepository.findByStatut(statut).stream()
                .map(contratMapper::fromAssuranceHabitation)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceHabitationDTO> getAssurancesHabitationByTypeLogement(TypeLogement typeLogement) {
        return habitationRepository.findByTypeLogement(typeLogement).stream()
                .map(contratMapper::fromAssuranceHabitation)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceHabitationDTO> searchAssurancesHabitationByAdresse(String adresse) {
        return habitationRepository.findByAdresseContainingIgnoreCase(adresse).stream()
                .map(contratMapper::fromAssuranceHabitation)
                .collect(Collectors.toList());
    }

    @Override
    public AssuranceHabitationDTO createAssuranceHabitation(AssuranceHabitationDTO assuranceHabitationDTO) {
        Client client = findClient(assuranceHabitationDTO.getClientId());
        AssuranceHabitation assurance = contratMapper.fromAssuranceHabitationDTO(assuranceHabitationDTO, client);
        assurance.setId(null);
        AssuranceHabitation savedAssurance = habitationRepository.save(assurance);
        return contratMapper.fromAssuranceHabitation(savedAssurance);
    }

    @Override
    public AssuranceHabitationDTO updateAssuranceHabitation(Long id, AssuranceHabitationDTO assuranceHabitationDTO) {
        AssuranceHabitation assurance = findAssurance(id);
        Client client = findClient(assuranceHabitationDTO.getClientId());

        assurance.setClient(client);
        assurance.setDateSouscription(assuranceHabitationDTO.getDateSouscription());
        assurance.setStatut(assuranceHabitationDTO.getStatut());
        assurance.setDateValidation(assuranceHabitationDTO.getDateValidation());
        assurance.setMontantCotisation(assuranceHabitationDTO.getMontantCotisation());
        assurance.setDureeContrat(assuranceHabitationDTO.getDureeContrat());
        assurance.setTauxCouverture(assuranceHabitationDTO.getTauxCouverture());
        assurance.setTypeLogement(assuranceHabitationDTO.getTypeLogement());
        assurance.setAdresse(assuranceHabitationDTO.getAdresse());
        assurance.setSuperficie(assuranceHabitationDTO.getSuperficie());

        AssuranceHabitation updatedAssurance = habitationRepository.save(assurance);
        return contratMapper.fromAssuranceHabitation(updatedAssurance);
    }

    @Override
    public AssuranceHabitationDTO validerAssuranceHabitation(Long id) {
        AssuranceHabitation assurance = findAssurance(id);
        assurance.setStatut(StatutContrat.VALIDE);
        assurance.setDateValidation(LocalDate.now());
        return contratMapper.fromAssuranceHabitation(habitationRepository.save(assurance));
    }

    @Override
    public AssuranceHabitationDTO resilierAssuranceHabitation(Long id) {
        AssuranceHabitation assurance = findAssurance(id);
        assurance.setStatut(StatutContrat.RESILIE);
        return contratMapper.fromAssuranceHabitation(habitationRepository.save(assurance));
    }

    @Override
    public void deleteAssuranceHabitation(Long id) {
        habitationRepository.delete(findAssurance(id));
    }

    private AssuranceHabitation findAssurance(Long id) {
        return habitationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assurance habitation", id));
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }
}
