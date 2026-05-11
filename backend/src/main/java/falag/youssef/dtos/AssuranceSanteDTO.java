package falag.youssef.dtos;

import falag.youssef.enums.NiveauCouverture;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssuranceSanteDTO extends ContratDTO {

    @NotNull(message = "Le niveau de couverture est obligatoire")
    private NiveauCouverture niveauCouverture;

    @Min(value = 1, message = "Au moins 1 personne doit être couverte")
    private int nbPersonnesCouvertes;
}