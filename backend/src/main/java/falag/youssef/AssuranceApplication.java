package falag.youssef;

import falag.youssef.entities.*;
import falag.youssef.enums.*;
import falag.youssef.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@Slf4j
public class AssuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssuranceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ClientRepository                 clientRepository,
            AssuranceAutomobileRepository    autoRepository,
            AssuranceHabitationRepository    habitationRepository,
            AssuranceSanteRepository         santeRepository,
            PaiementRepository               paiementRepository
    ) {
        return args -> {


            // 1. CLIENTS
            Client client1 = new Client();
            client1.setNom("Alaoui Karim");
            client1.setEmail("karim.alaoui@gmail.com");
            clientRepository.save(client1);

            Client client2 = new Client();
            client2.setNom("El Fassi Sanaa");
            client2.setEmail("sanaa.elfassi@gmail.com");
            clientRepository.save(client2);

            Client client3 = new Client();
            client3.setNom("Tahiri Omar");
            client3.setEmail("omar.tahiri@gmail.com");
            clientRepository.save(client3);


            // 2. CONTRATS AUTOMOBILE
            AssuranceAutomobile auto1 = new AssuranceAutomobile();
            auto1.setDateSouscription(LocalDate.of(2024, 1, 15));
            auto1.setStatut(StatutContrat.EN_COURS);
            auto1.setDateValidation(LocalDate.of(2024, 1, 20));
            auto1.setMontantCotisation(new BigDecimal("1200.00"));
            auto1.setDureeContrat(12);
            auto1.setTauxCouverture(new BigDecimal("80.00"));
            auto1.setClient(client1);
            auto1.setNumImmatriculation("12345-A-1");
            auto1.setMarque("Toyota");
            auto1.setModele("Corolla");
            autoRepository.save(auto1);

            AssuranceAutomobile auto2 = new AssuranceAutomobile();
            auto2.setDateSouscription(LocalDate.of(2024, 3, 10));
            auto2.setStatut(StatutContrat.VALIDE);
            auto2.setDateValidation(LocalDate.of(2024, 3, 12));
            auto2.setMontantCotisation(new BigDecimal("1800.00"));
            auto2.setDureeContrat(24);
            auto2.setTauxCouverture(new BigDecimal("90.00"));
            auto2.setClient(client2);
            auto2.setNumImmatriculation("67890-B-2");
            auto2.setMarque("Renault");
            auto2.setModele("Clio");
            autoRepository.save(auto2);

            // 3. CONTRATS HABITATION
            AssuranceHabitation hab1 = new AssuranceHabitation();
            hab1.setDateSouscription(LocalDate.of(2024, 2, 1));
            hab1.setStatut(StatutContrat.EN_COURS);
            hab1.setDateValidation(LocalDate.of(2024, 2, 5));
            hab1.setMontantCotisation(new BigDecimal("950.00"));
            hab1.setDureeContrat(12);
            hab1.setTauxCouverture(new BigDecimal("75.00"));
            hab1.setClient(client1);
            hab1.setTypeLogement(TypeLogement.APPARTEMENT);
            hab1.setAdresse("12 Rue Hassan II, Casablanca");
            hab1.setSuperficie(new BigDecimal("85.50"));
            habitationRepository.save(hab1);

            AssuranceHabitation hab2 = new AssuranceHabitation();
            hab2.setDateSouscription(LocalDate.of(2023, 11, 20));
            hab2.setStatut(StatutContrat.RESILIE);
            hab2.setDateValidation(LocalDate.of(2023, 11, 25));
            hab2.setMontantCotisation(new BigDecimal("2200.00"));
            hab2.setDureeContrat(36);
            hab2.setTauxCouverture(new BigDecimal("85.00"));
            hab2.setClient(client3);
            hab2.setTypeLogement(TypeLogement.MAISON);
            hab2.setAdresse("5 Avenue Mohammed V, Rabat");
            hab2.setSuperficie(new BigDecimal("180.00"));
            habitationRepository.save(hab2);

            // 4. CONTRATS SANTÉ
            AssuranceSante sante1 = new AssuranceSante();
            sante1.setDateSouscription(LocalDate.of(2024, 4, 1));
            sante1.setStatut(StatutContrat.EN_COURS);
            sante1.setDateValidation(LocalDate.of(2024, 4, 3));
            sante1.setMontantCotisation(new BigDecimal("3600.00"));
            sante1.setDureeContrat(12);
            sante1.setTauxCouverture(new BigDecimal("95.00"));
            sante1.setClient(client2);
            sante1.setNiveauCouverture(NiveauCouverture.PREMIUM);
            sante1.setNbPersonnesCouvertes(4);
            santeRepository.save(sante1);

            AssuranceSante sante2 = new AssuranceSante();
            sante2.setDateSouscription(LocalDate.of(2024, 6, 15));
            sante2.setStatut(StatutContrat.VALIDE);
            sante2.setDateValidation(LocalDate.of(2024, 6, 18));
            sante2.setMontantCotisation(new BigDecimal("1500.00"));
            sante2.setDureeContrat(12);
            sante2.setTauxCouverture(new BigDecimal("60.00"));
            sante2.setClient(client3);
            sante2.setNiveauCouverture(NiveauCouverture.BASIQUE);
            sante2.setNbPersonnesCouvertes(1);
            santeRepository.save(sante2);


            // 5. PAIEMENTS
            Paiement p1 = new Paiement();
            p1.setDate(LocalDate.of(2024, 1, 20));
            p1.setMontant(new BigDecimal("100.00"));
            p1.setType(TypePaiement.MENSUALITE);
            p1.setContrat(auto1);
            paiementRepository.save(p1);

            Paiement p2 = new Paiement();
            p2.setDate(LocalDate.of(2024, 2, 20));
            p2.setMontant(new BigDecimal("100.00"));
            p2.setType(TypePaiement.MENSUALITE);
            p2.setContrat(auto1);
            paiementRepository.save(p2);

            Paiement p3 = new Paiement();
            p3.setDate(LocalDate.of(2024, 3, 12));
            p3.setMontant(new BigDecimal("1800.00"));
            p3.setType(TypePaiement.PAIEMENT_ANNUEL);
            p3.setContrat(auto2);
            paiementRepository.save(p3);

            Paiement p4 = new Paiement();
            p4.setDate(LocalDate.of(2024, 2, 5));
            p4.setMontant(new BigDecimal("950.00"));
            p4.setType(TypePaiement.PAIEMENT_ANNUEL);
            p4.setContrat(hab1);
            paiementRepository.save(p4);

            Paiement p5 = new Paiement();
            p5.setDate(LocalDate.of(2024, 4, 3));
            p5.setMontant(new BigDecimal("300.00"));
            p5.setType(TypePaiement.MENSUALITE);
            p5.setContrat(sante1);
            paiementRepository.save(p5);

            Paiement p6 = new Paiement();
            p6.setDate(LocalDate.of(2024, 5, 3));
            p6.setMontant(new BigDecimal("300.00"));
            p6.setType(TypePaiement.MENSUALITE);
            p6.setContrat(sante1);
            paiementRepository.save(p6);

            Paiement p7 = new Paiement();
            p7.setDate(LocalDate.of(2024, 7, 1));
            p7.setMontant(new BigDecimal("250.00"));
            p7.setType(TypePaiement.PAIEMENT_EXCEPTIONNEL);
            p7.setContrat(sante2);
            paiementRepository.save(p7);

        };
    }
}