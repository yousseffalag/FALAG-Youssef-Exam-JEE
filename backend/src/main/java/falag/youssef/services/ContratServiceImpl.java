package falag.youssef.services;

import falag.youssef.dtos.ContratDTO;
import falag.youssef.entities.ContratAssurance;
import falag.youssef.enums.StatutContrat;
import falag.youssef.mappers.ContratMapper;
import falag.youssef.repositories.ContratAssuranceRepository;
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
public class ContratServiceImpl implements ContratService {

    private ContratAssuranceRepository contratRepository;
    private ContratMapper contratMapper;

    @Override
    public ContratDTO getContrat(Long id) {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
        return contratMapper.fromContrat(contrat);
    }

    @Override
    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(contratMapper::fromContrat)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratDTO> getContratsByClient(Long clientId) {
        return contratRepository.findByClientId(clientId).stream()
                .map(contratMapper::fromContrat)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratDTO> getContratsByStatut(StatutContrat statut) {
        return contratRepository.findByStatut(statut).stream()
                .map(contratMapper::fromContrat)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratDTO> getContratsByClientAndStatut(Long clientId, StatutContrat statut) {
        return contratRepository.findByClientIdAndStatut(clientId, statut).stream()
                .map(contratMapper::fromContrat)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratDTO> getContratsByDateSouscription(LocalDate debut, LocalDate fin) {
        return contratRepository.findByDateSouscriptionBetween(debut, fin).stream()
                .map(contratMapper::fromContrat)
                .collect(Collectors.toList());
    }
}
