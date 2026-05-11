package falag.youssef.dtos;

import  falag.youssef.enums.TypeLogement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AssuranceHabitationDTO extends ContratDTO {

    @NotNull(message = "Le type de logement est obligatoire")
    private TypeLogement typeLogement;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotNull(message = "La superficie est obligatoire")
    @DecimalMin(value = "1.0", message = "La superficie doit être supérieure à 0")
    private BigDecimal superficie;
}