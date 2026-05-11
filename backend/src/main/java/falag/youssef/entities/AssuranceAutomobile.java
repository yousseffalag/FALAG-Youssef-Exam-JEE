package falag.youssef.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assurance_automobile")
@DiscriminatorValue("AUTOMOBILE")
@PrimaryKeyJoinColumn(name = "contrat_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssuranceAutomobile extends ContratAssurance {

    @Column(nullable = false)
    private String numImmatriculation;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String modele;
}