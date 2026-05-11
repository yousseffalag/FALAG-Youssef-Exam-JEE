package falag.youssef.dtos;


import falag.youssef.enums.StatutContrat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ContratDTO {

    private Long id;

    @NotNull(message = "L'ID client est obligatoire")
    private Long clientId;

    @NotNull(message = "La date de souscription est obligatoire")
    private LocalDate dateSouscription;

    private StatutContrat statut = StatutContrat.EN_COURS;

    private LocalDate dateValidation;

    @NotNull(message = "Le montant de la cotisation est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "La cotisation doit être positive")
    private BigDecimal montantCotisation;

    @Min(value = 1, message = "La durée doit être d'au moins 1 mois")
    private int dureeContrat;

    @DecimalMin(value = "0.0", message = "Le taux de couverture doit être positif")
    private BigDecimal tauxCouverture;
}
