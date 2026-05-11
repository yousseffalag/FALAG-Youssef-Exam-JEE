package falag.youssef.web;

import falag.youssef.dtos.ContratDTO;
import falag.youssef.enums.StatutContrat;
import falag.youssef.services.ContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrats")
@AllArgsConstructor
public class ContratRestController {

    private ContratService contratService;

    @GetMapping
    @Operation(summary = "Lister les contrats")
    public List<ContratDTO> getContrats(@RequestParam(required = false) Long clientId,
                                        @RequestParam(required = false) StatutContrat statut,
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        if (clientId != null && statut != null) {
            return contratService.getContratsByClientAndStatut(clientId, statut);
        }
        if (clientId != null) {
            return contratService.getContratsByClient(clientId);
        }
        if (statut != null) {
            return contratService.getContratsByStatut(statut);
        }
        if (debut != null && fin != null) {
            return contratService.getContratsByDateSouscription(debut, fin);
        }
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un contrat")
    public ContratDTO getContrat(@PathVariable Long id) {
        return contratService.getContrat(id);
    }
}
