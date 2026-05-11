package falag.youssef.services;

import falag.youssef.dtos.ClientDTO;
import falag.youssef.dtos.ContratDTO;
import falag.youssef.entities.Client;
import falag.youssef.mappers.ClientMapper;
import falag.youssef.mappers.ContratMapper;
import falag.youssef.repositories.ClientRepository;
import falag.youssef.repositories.ContratAssuranceRepository;
import falag.youssef.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ContratAssuranceRepository contratRepository;
    private ClientMapper clientMapper;
    private ContratMapper contratMapper;

    @Override
    public ClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        return clientMapper.fromClient(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> searchClientsByNom(String nom) {
        return clientRepository.findByNomContainingIgnoreCase(nom).stream()
                .map(clientMapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratDTO> getClientContrats(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }

        return contratRepository.findByClientId(id).stream()
                .map(contratMapper::fromContrat)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        if (clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new RuntimeException("Client already exists with email: " + clientDTO.getEmail());
        }

        Client client = clientMapper.fromClientDTO(clientDTO);
        client.setId(null);
        Client savedClient = clientRepository.save(client);
        return clientMapper.fromClient(savedClient);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));

        clientRepository.findByEmail(clientDTO.getEmail())
                .filter(existingClient -> !existingClient.getId().equals(id))
                .ifPresent(existingClient -> {
                    throw new RuntimeException("Another client already exists with email: " + clientDTO.getEmail());
                });

        client.setNom(clientDTO.getNom());
        client.setEmail(clientDTO.getEmail());

        Client updatedClient = clientRepository.save(client);
        return clientMapper.fromClient(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        clientRepository.deleteById(id);
    }
}
