package falag.youssef.web;

import falag.youssef.dtos.PaiementDTO;
import falag.youssef.enums.TypePaiement;
import falag.youssef.services.PaiementService;
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

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Paiements")
@AllArgsConstructor
public class PaiementRestController {

    private PaiementService paiementService;

    @GetMapping
    @Operation(summary = "Lister les paiements")
    public List<PaiementDTO> getPaiements(@RequestParam(required = false) Long contratId,
                                          @RequestParam(required = false) TypePaiement type) {
        if (contratId != null && type != null) return paiementService.getPaiementsByContratAndType(contratId, type);
        if (contratId != null) return paiementService.getPaiementsByContrat(contratId);
        if (type != null) return paiementService.getPaiementsByType(type);
        return paiementService.getAllPaiements();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un paiement")
    public PaiementDTO getPaiement(@PathVariable Long id) {
        return paiementService.getPaiement(id);
    }

    @GetMapping("/contrat/{contratId}/total")
    @Operation(summary = "Calculer le total payé pour un contrat")
    public BigDecimal getTotalByContrat(@PathVariable Long contratId) {
        return paiementService.getTotalByContrat(contratId);
    }

    @GetMapping("/client/{clientId}/total")
    @Operation(summary = "Calculer le total payé par un client")
    public BigDecimal getTotalByClient(@PathVariable Long clientId) {
        return paiementService.getTotalByClient(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un paiement")
    public PaiementDTO createPaiement(@Valid @RequestBody PaiementDTO paiementDTO) {
        return paiementService.createPaiement(paiementDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    public PaiementDTO updatePaiement(@PathVariable Long id, @Valid @RequestBody PaiementDTO paiementDTO) {
        return paiementService.updatePaiement(id, paiementDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un paiement")
    public void deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
    }
}
