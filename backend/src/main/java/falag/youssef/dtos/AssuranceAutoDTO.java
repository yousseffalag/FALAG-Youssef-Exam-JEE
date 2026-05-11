package falag.youssef.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssuranceAutoDTO extends ContratDTO {

    @NotBlank(message = "Le numéro d'immatriculation est obligatoire")
    private String numImmatriculation;

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;
}