package falag.youssef.entities;

import falag.youssef.enums.StatutContrat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrat_assurance")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_contrat", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ContratAssurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateSouscription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutContrat statut;

    private LocalDate dateValidation;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantCotisation;

    @Column(nullable = false)
    private Integer dureeContrat;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal tauxCouverture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Paiement> paiements = new ArrayList<>();
}