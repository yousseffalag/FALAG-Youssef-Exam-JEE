package falag.youssef.web;

import falag.youssef.dtos.AssuranceSanteDTO;
import falag.youssef.enums.NiveauCouverture;
import falag.youssef.enums.StatutContrat;
import falag.youssef.services.AssuranceSanteService;
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
@RequestMapping("/api/assurances/sante")
@Tag(name = "Assurances santé")
@AllArgsConstructor
public class AssuranceSanteRestController {

    private AssuranceSanteService santeService;

    @GetMapping
    @Operation(summary = "Lister les assurances santé")
    public List<AssuranceSanteDTO> getAssurances(@RequestParam(required = false) Long clientId,
                                                 @RequestParam(required = false) StatutContrat statut,
                                                 @RequestParam(required = false) NiveauCouverture niveauCouverture,
                                                 @RequestParam(required = false) Integer minPersonnes) {
        if (clientId != null) return santeService.getAssurancesSanteByClient(clientId);
        if (statut != null) return santeService.getAssurancesSanteByStatut(statut);
        if (niveauCouverture != null) return santeService.getAssurancesSanteByNiveauCouverture(niveauCouverture);
        if (minPersonnes != null) return santeService.getAssurancesSanteByMinPersonnes(minPersonnes);
        return santeService.getAllAssurancesSante();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une assurance santé")
    public AssuranceSanteDTO getAssurance(@PathVariable Long id) {
        return santeService.getAssuranceSante(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une assurance santé")
    public AssuranceSanteDTO createAssurance(@Valid @RequestBody AssuranceSanteDTO assuranceSanteDTO) {
        return santeService.createAssuranceSante(assuranceSanteDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une assurance santé")
    public AssuranceSanteDTO updateAssurance(@PathVariable Long id, @Valid @RequestBody AssuranceSanteDTO assuranceSanteDTO) {
        return santeService.updateAssuranceSante(id, assuranceSanteDTO);
    }

    @PatchMapping("/{id}/valider")
    @Operation(summary = "Valider une assurance santé")
    public AssuranceSanteDTO validerAssurance(@PathVariable Long id) {
        return santeService.validerAssuranceSante(id);
    }

    @PatchMapping("/{id}/resilier")
    @Operation(summary = "Résilier une assurance santé")
    public AssuranceSanteDTO resilierAssurance(@PathVariable Long id) {
        return santeService.resilierAssuranceSante(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une assurance santé")
    public void deleteAssurance(@PathVariable Long id) {
        santeService.deleteAssuranceSante(id);
    }
}
