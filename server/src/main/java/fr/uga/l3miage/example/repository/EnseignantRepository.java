package fr.uga.l3miage.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.uga.l3miage.example.models.Enseignant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {


    Optional<Enseignant> findByMail(final String mail);
    Optional<Enseignant> findByPseudo(final String pseudo);
    int deleteByMail(final String mail);

    Optional<Enseignant> findById(final long id);

    int deleteById(final long id);

}
