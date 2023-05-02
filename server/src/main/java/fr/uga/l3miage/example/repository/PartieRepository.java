package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface PartieRepository extends JpaRepository<Partie, Long> {


    Optional<Partie> findByCodePartie(Long codePartie);

    <S extends Partie> S save(S s);

    int deleteById(final long id);


}
