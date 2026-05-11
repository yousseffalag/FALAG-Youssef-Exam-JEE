package falag.youssef.entities;
import falag.youssef.enums.NiveauCouverture;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assurance_sante")
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