package falag.youssef.services;

import falag.youssef.dtos.PaiementDTO;
import falag.youssef.entities.ContratAssurance;
import falag.youssef.entities.Paiement;
import falag.youssef.enums.TypePaiement;
import falag.youssef.mappers.PaiementMapper;
import falag.youssef.repositories.ClientRepository;
import falag.youssef.repositories.ContratAssuranceRepository;
import falag.youssef.repositories.PaiementRepository;
import falag.youssef.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private PaiementRepository paiementRepository;
    private ContratAssuranceRepository contratRepository;
    private ClientRepository clientRepository;
    private PaiementMapper paiementMapper;

    @Override
    public PaiementDTO getPaiement(Long id) {
        return paiementMapper.fromPaiement(findPaiement(id));
    }

    @Override
    public List<PaiementDTO> getAllPaiements() {
        return paiementRepository.findAll().stream()
                .map(paiementMapper::fromPaiement)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId).stream()
                .map(paiementMapper::fromPaiement)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaiementDTO> getPaiementsByType(TypePaiement type) {
        return paiementRepository.findByType(type).stream()
                .map(paiementMapper::fromPaiement)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaiementDTO> getPaiementsByContratAndType(Long contratId, TypePaiement type) {
        return paiementRepository.findByContratIdAndType(contratId, type).stream()
                .map(paiementMapper::fromPaiement)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalByContrat(Long contratId) {
        if (!contratRepository.existsById(contratId)) {
            throw new ResourceNotFoundException("Contrat", contratId);
        }

        BigDecimal total = paiementRepository.sumMontantByContratId(contratId);
        return total == null ? BigDecimal.ZERO : total;
    }

    @Override
    public BigDecimal getTotalByClient(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Client", clientId);
        }

        BigDecimal total = paiementRepository.sumMontantByClientId(clientId);
        return total == null ? BigDecimal.ZERO : total;
    }

    @Override
    public PaiementDTO createPaiement(PaiementDTO paiementDTO) {
        ContratAssurance contrat = findContrat(paiementDTO.getContratId());
        Paiement paiement = paiementMapper.fromPaiementDTO(paiementDTO, contrat);
        paiement.setId(null);
        Paiement savedPaiement = paiementRepository.save(paiement);
        return paiementMapper.fromPaiement(savedPaiement);
    }

    @Override
    public PaiementDTO updatePaiement(Long id, PaiementDTO paiementDTO) {
        Paiement paiement = findPaiement(id);
        ContratAssurance contrat = findContrat(paiementDTO.getContratId());

        paiement.setContrat(contrat);
        paiement.setDate(paiementDTO.getDate());
        paiement.setMontant(paiementDTO.getMontant());
        paiement.setType(paiementDTO.getType());

        Paiement updatedPaiement = paiementRepository.save(paiement);
        return paiementMapper.fromPaiement(updatedPaiement);
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.delete(findPaiement(id));
    }

    private Paiement findPaiement(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
    }

    private ContratAssurance findContrat(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }
}
