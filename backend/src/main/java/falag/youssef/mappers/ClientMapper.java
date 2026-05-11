package falag.youssef.mappers;

import falag.youssef.dtos.ClientDTO;
import falag.youssef.entities.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    public ClientDTO fromClient(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());

        return dto;
    }

    public Client fromClientDTO(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());

        return client;
    }
}
