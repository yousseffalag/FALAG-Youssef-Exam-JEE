package falag.youssef.services;

import falag.youssef.dtos.AssuranceSanteDTO;
import falag.youssef.entities.AssuranceSante;
import falag.youssef.entities.Client;
import falag.youssef.enums.NiveauCouverture;
import falag.youssef.enums.StatutContrat;
import falag.youssef.mappers.ContratMapper;
import falag.youssef.repositories.AssuranceSanteRepository;
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
public class AssuranceSanteServiceImpl implements AssuranceSanteService {

    private AssuranceSanteRepository santeRepository;
    private ClientRepository clientRepository;
    private ContratMapper contratMapper;

    @Override
    public AssuranceSanteDTO getAssuranceSante(Long id) {
        return contratMapper.fromAssuranceSante(findAssurance(id));
    }

    @Override
    public List<AssuranceSanteDTO> getAllAssurancesSante() {
        return santeRepository.findAll().stream()
                .map(contratMapper::fromAssuranceSante)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceSanteDTO> getAssurancesSanteByClient(Long clientId) {
        return santeRepository.findByClientId(clientId).stream()
                .map(contratMapper::fromAssuranceSante)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceSanteDTO> getAssurancesSanteByStatut(StatutContrat statut) {
        return santeRepository.findByStatut(statut).stream()
                .map(contratMapper::fromAssuranceSante)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceSanteDTO> getAssurancesSanteByNiveauCouverture(NiveauCouverture niveauCouverture) {
        return santeRepository.findByNiveauCouverture(niveauCouverture).stream()
                .map(contratMapper::fromAssuranceSante)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssuranceSanteDTO> getAssurancesSanteByMinPersonnes(Integer minPersonnes) {
        return santeRepository.findByNbPersonnesCouvertesGreaterThanEqual(minPersonnes).stream()
                .map(contratMapper::fromAssuranceSante)
                .collect(Collectors.toList());
    }

    @Override
    public AssuranceSanteDTO createAssuranceSante(AssuranceSanteDTO assuranceSanteDTO) {
        Client client = findClient(assuranceSanteDTO.getClientId());
        AssuranceSante assurance = contratMapper.fromAssuranceSanteDTO(assuranceSanteDTO, client);
        assurance.setId(null);
        AssuranceSante savedAssurance = santeRepository.save(assurance);
        return contratMapper.fromAssuranceSante(savedAssurance);
    }

    @Override
    public AssuranceSanteDTO updateAssuranceSante(Long id, AssuranceSanteDTO assuranceSanteDTO) {
        AssuranceSante assurance = findAssurance(id);
        Client client = findClient(assuranceSanteDTO.getClientId());

        assurance.setClient(client);
        assurance.setDateSouscription(assuranceSanteDTO.getDateSouscription());
        assurance.setStatut(assuranceSanteDTO.getStatut());
        assurance.setDateValidation(assuranceSanteDTO.getDateValidation());
        assurance.setMontantCotisation(assuranceSanteDTO.getMontantCotisation());
        assurance.setDureeContrat(assuranceSanteDTO.getDureeContrat());
        assurance.setTauxCouverture(assuranceSanteDTO.getTauxCouverture());
        assurance.setNiveauCouverture(assuranceSanteDTO.getNiveauCouverture());
        assurance.setNbPersonnesCouvertes(assuranceSanteDTO.getNbPersonnesCouvertes());

        AssuranceSante updatedAssurance = santeRepository.save(assurance);
        return contratMapper.fromAssuranceSante(updatedAssurance);
    }

    @Override
    public AssuranceSanteDTO validerAssuranceSante(Long id) {
        AssuranceSante assurance = findAssurance(id);
        assurance.setStatut(StatutContrat.VALIDE);
        assurance.setDateValidation(LocalDate.now());
        return contratMapper.fromAssuranceSante(santeRepository.save(assurance));
    }

    @Override
    public AssuranceSanteDTO resilierAssuranceSante(Long id) {
        AssuranceSante assurance = findAssurance(id);
        assurance.setStatut(StatutContrat.RESILIE);
        return contratMapper.fromAssuranceSante(santeRepository.save(assurance));
    }

    @Override
    public void deleteAssuranceSante(Long id) {
        santeRepository.delete(findAssurance(id));
    }

    private AssuranceSante findAssurance(Long id) {
        return santeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assurance santé", id));
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }
}
