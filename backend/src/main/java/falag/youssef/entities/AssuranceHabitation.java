package falag.youssef.entities;
import falag.youssef.enums.TypeLogement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "assurance_habitation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssuranceHabitation extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeLogement typeLogement;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal superficie;
}
