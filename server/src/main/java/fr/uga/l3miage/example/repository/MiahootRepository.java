package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Miahoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiahootRepository extends JpaRepository<Miahoot, Long> {

    Optional<Miahoot> findById(final Long id);
    void deleteById(final Long id);
}
