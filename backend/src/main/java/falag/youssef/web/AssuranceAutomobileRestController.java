package falag.youssef.web;

import falag.youssef.dtos.AssuranceAutoDTO;
import falag.youssef.enums.StatutContrat;
import falag.youssef.services.AssuranceAutomobileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/api/assurances/automobiles")
@Tag(name = "Assurances automobile")
@AllArgsConstructor
public class AssuranceAutomobileRestController {

    private AssuranceAutomobileService automobileService;

    @GetMapping
    @Operation(summary = "Lister les assurances automobile")
    public List<AssuranceAutoDTO> getAssurances(@RequestParam(required = false) Long clientId,
                                                @RequestParam(required = false) StatutContrat statut,
                                                @RequestParam(required = false) String marque) {
        if (clientId != null) return automobileService.getAssurancesAutomobileByClient(clientId);
        if (statut != null) return automobileService.getAssurancesAutomobileByStatut(statut);
        if (marque != null && !marque.isBlank()) return automobileService.getAssurancesAutomobileByMarque(marque);
        return automobileService.getAllAssurancesAutomobile();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une assurance automobile")
    public AssuranceAutoDTO getAssurance(@PathVariable Long id) {
        return automobileService.getAssuranceAutomobile(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une assurance automobile")
    public AssuranceAutoDTO createAssurance(@Valid @RequestBody AssuranceAutoDTO assuranceAutoDTO) {
        return automobileService.createAssuranceAutomobile(assuranceAutoDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une assurance automobile")
    public AssuranceAutoDTO updateAssurance(@PathVariable Long id, @Valid @RequestBody AssuranceAutoDTO assuranceAutoDTO) {
        return automobileService.updateAssuranceAutomobile(id, assuranceAutoDTO);
    }

    @PatchMapping("/{id}/valider")
    @Operation(summary = "Valider une assurance automobile")
    public AssuranceAutoDTO validerAssurance(@PathVariable Long id) {
        return automobileService.validerAssuranceAutomobile(id);
    }

    @PatchMapping("/{id}/resilier")
    @Operation(summary = "Résilier une assurance automobile")
    public AssuranceAutoDTO resilierAssurance(@PathVariable Long id) {
        return automobileService.resilierAssuranceAutomobile(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une assurance automobile")
    public void deleteAssurance(@PathVariable Long id) {
        automobileService.deleteAssuranceAutomobile(id);
    }
}
