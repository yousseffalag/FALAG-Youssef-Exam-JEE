package falag.youssef.repositories;

import falag.youssef.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Client> findByNomContainingIgnoreCase(String nom);

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.contrats WHERE c.id = :id")
    Optional<Client> findByIdWithContrats(Long id);
}