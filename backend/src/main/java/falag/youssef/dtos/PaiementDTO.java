package falag.youssef.dtos;

import falag.youssef.enums.TypePaiement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PaiementDTO {

    @NotNull(message = "L'ID du contrat est obligatoire")
    private Long contratId;

    @NotNull(message = "La date de paiement est obligatoire")
    private LocalDate date;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant doit être positif")
    private BigDecimal montant;

    @NotNull(message = "Le type de paiement est obligatoire")
    private TypePaiement type;
}