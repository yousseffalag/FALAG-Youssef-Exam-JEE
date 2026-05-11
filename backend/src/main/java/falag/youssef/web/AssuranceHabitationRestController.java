package falag.youssef.web;

import falag.youssef.dtos.AssuranceHabitationDTO;
import falag.youssef.enums.StatutContrat;
import falag.youssef.enums.TypeLogement;
import falag.youssef.services.AssuranceHabitationService;
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
@RequestMapping("/api/assurances/habitations")
@Tag(name = "Assurances habitation")
@AllArgsConstructor
public class AssuranceHabitationRestController {

    private AssuranceHabitationService habitationService;

    @GetMapping
    @Operation(summary = "Lister les assurances habitation")
    public List<AssuranceHabitationDTO> getAssurances(@RequestParam(required = false) Long clientId,
                                                      @RequestParam(required = false) StatutContrat statut,
                                                      @RequestParam(required = false) TypeLogement typeLogement,
                                                      @RequestParam(required = false) String adresse) {
        if (clientId != null) return habitationService.getAssurancesHabitationByClient(clientId);
        if (statut != null) return habitationService.getAssurancesHabitationByStatut(statut);
        if (typeLogement != null) return habitationService.getAssurancesHabitationByTypeLogement(typeLogement);
        if (adresse != null && !adresse.isBlank()) return habitationService.searchAssurancesHabitationByAdresse(adresse);
        return habitationService.getAllAssurancesHabitation();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une assurance habitation")
    public AssuranceHabitationDTO getAssurance(@PathVariable Long id) {
        return habitationService.getAssuranceHabitation(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une assurance habitation")
    public AssuranceHabitationDTO createAssurance(@Valid @RequestBody AssuranceHabitationDTO assuranceHabitationDTO) {
        return habitationService.createAssuranceHabitation(assuranceHabitationDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une assurance habitation")
    public AssuranceHabitationDTO updateAssurance(@PathVariable Long id, @Valid @RequestBody AssuranceHabitationDTO assuranceHabitationDTO) {
        return habitationService.updateAssuranceHabitation(id, assuranceHabitationDTO);
    }

    @PatchMapping("/{id}/valider")
    @Operation(summary = "Valider une assurance habitation")
    public AssuranceHabitationDTO validerAssurance(@PathVariable Long id) {
        return habitationService.validerAssuranceHabitation(id);
    }

    @PatchMapping("/{id}/resilier")
    @Operation(summary = "Résilier une assurance habitation")
    public AssuranceHabitationDTO resilierAssurance(@PathVariable Long id) {
        return habitationService.resilierAssuranceHabitation(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une assurance habitation")
    public void deleteAssurance(@PathVariable Long id) {
        habitationService.deleteAssuranceHabitation(id);
    }
}
