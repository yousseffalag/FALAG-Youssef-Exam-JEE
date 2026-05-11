package falag.youssef.services;

import falag.youssef.dtos.AssuranceAutoDTO;
import falag.youssef.entities.AssuranceAutomobile;
import falag.youssef.entities.Client;
import falag.youssef.enums.StatutContrat;
import falag.youssef.mappers.ContratMapper;
import falag.youssef.repositories.AssuranceAutomobileRepository;
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
public class AssuranceAutomobileServiceImpl implements AssuranceAutomobileService {

    private AssuranceAutomobileRepository automobileRepository;
    private ClientRepository clientRepository;
    private ContratMapper contratMapper;

    @Override
    public AssuranceAutoDTO getAssuranceAutomobile(Long id) {
        return contratMapper.fromAssuranceAutomobile(findAssurance(id));
    }

    @Override
    public List<AssuranceAutoDTO> getAllAssurancesAutomobile() {
        return automobileRepository.findAll().stream()
                .map(contratMapper::fromAssuranceAutomobile)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceAutoDTO> getAssurancesAutomobileByClient(Long clientId) {
        return automobileRepository.findByClientId(clientId).stream()
                .map(contratMapper::fromAssuranceAutomobile)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceAutoDTO> getAssurancesAutomobileByStatut(StatutContrat statut) {
        return automobileRepository.findByStatut(statut).stream()
                .map(contratMapper::fromAssuranceAutomobile)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceAutoDTO> getAssurancesAutomobileByMarque(String marque) {
        return automobileRepository.findByMarqueIgnoreCase(marque).stream()
                .map(contratMapper::fromAssuranceAutomobile)
                .collect(Collectors.toList());
    }

    @Override
    public AssuranceAutoDTO createAssuranceAutomobile(AssuranceAutoDTO assuranceAutoDTO) {
        if (automobileRepository.existsByNumImmatriculation(assuranceAutoDTO.getNumImmatriculation())) {
            throw new RuntimeException("Assurance automobile already exists with immatriculation: " + assuranceAutoDTO.getNumImmatriculation());
        }

        Client client = findClient(assuranceAutoDTO.getClientId());
        AssuranceAutomobile assurance = contratMapper.fromAssuranceAutoDTO(assuranceAutoDTO, client);
        assurance.setId(null);
        AssuranceAutomobile savedAssurance = automobileRepository.save(assurance);
        return contratMapper.fromAssuranceAutomobile(savedAssurance);
    }

    @Override
    public AssuranceAutoDTO updateAssuranceAutomobile(Long id, AssuranceAutoDTO assuranceAutoDTO) {
        AssuranceAutomobile assurance = findAssurance(id);
        Client client = findClient(assuranceAutoDTO.getClientId());

        assurance.setClient(client);
        assurance.setDateSouscription(assuranceAutoDTO.getDateSouscription());
        assurance.setStatut(assuranceAutoDTO.getStatut());
        assurance.setDateValidation(assuranceAutoDTO.getDateValidation());
        assurance.setMontantCotisation(assuranceAutoDTO.getMontantCotisation());
        assurance.setDureeContrat(assuranceAutoDTO.getDureeContrat());
        assurance.setTauxCouverture(assuranceAutoDTO.getTauxCouverture());
        assurance.setNumImmatriculation(assuranceAutoDTO.getNumImmatriculation());
        assurance.setMarque(assuranceAutoDTO.getMarque());
        assurance.setModele(assuranceAutoDTO.getModele());

        AssuranceAutomobile updatedAssurance = automobileRepository.save(assurance);
        return contratMapper.fromAssuranceAutomobile(updatedAssurance);
    }

    @Override
    public AssuranceAutoDTO validerAssuranceAutomobile(Long id) {
        AssuranceAutomobile assurance = findAssurance(id);
        assurance.setStatut(StatutContrat.VALIDE);
        assurance.setDateValidation(LocalDate.now());
        return contratMapper.fromAssuranceAutomobile(automobileRepository.save(assurance));
    }

    @Override
    public AssuranceAutoDTO resilierAssuranceAutomobile(Long id) {
        AssuranceAutomobile assurance = findAssurance(id);
        assurance.setStatut(StatutContrat.RESILIE);
        return contratMapper.fromAssuranceAutomobile(automobileRepository.save(assurance));
    }

    @Override
    public void deleteAssuranceAutomobile(Long id) {
        automobileRepository.delete(findAssurance(id));
    }

    private AssuranceAutomobile findAssurance(Long id) {
        return automobileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assurance automobile", id));
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }
}
