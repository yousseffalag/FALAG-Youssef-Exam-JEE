package falag.youssef.web;

import falag.youssef.dtos.ClientDTO;
import falag.youssef.dtos.ContratDTO;
import falag.youssef.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients")
@AllArgsConstructor
public class ClientRestController {

    private ClientService clientService;

    @GetMapping
    @Operation(summary = "Lister les clients")
    public List<ClientDTO> getClients(@RequestParam(required = false) String nom) {
        if (nom != null && !nom.isBlank()) {
            return clientService.searchClientsByNom(nom);
        }
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un client")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @GetMapping("/{id}/contrats")
    @Operation(summary = "Lister les contrats d'un client")
    public List<ContratDTO> getClientContrats(@PathVariable Long id) {
        return clientService.getClientContrats(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un client")
    public ClientDTO createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    public ClientDTO updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un client")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
