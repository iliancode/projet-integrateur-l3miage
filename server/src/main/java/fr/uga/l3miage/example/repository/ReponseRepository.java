package fr.uga.l3miage.example.repository;
import fr.uga.l3miage.example.models.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Long> {

    Optional<Reponse> findByLabel(final String label);
    int deleteByLabel(final String label);

    Optional<Reponse> findById(final long id);
    int deleteById(final long id);


}
