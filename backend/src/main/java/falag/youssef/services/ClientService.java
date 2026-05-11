package falag.youssef.services;

import falag.youssef.dtos.ClientDTO;
import falag.youssef.dtos.ContratDTO;

import java.util.List;

public interface ClientService {
    ClientDTO getClient(Long id);
    List<ClientDTO> getAllClients();
    List<ClientDTO> searchClientsByNom(String nom);
    List<ContratDTO> getClientContrats(Long id);
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
}
