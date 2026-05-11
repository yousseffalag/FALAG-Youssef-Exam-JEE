package falag.youssef.entities;
import falag.youssef.enums.NiveauCouverture;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assurance_sante")
@DiscriminatorValue("SANTE")
@PrimaryKeyJoinColumn(name = "contrat_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssuranceSante extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NiveauCouverture niveauCouverture;

    @Column(nullable = false)
    private Integer nbPersonnesCouvertes;
}