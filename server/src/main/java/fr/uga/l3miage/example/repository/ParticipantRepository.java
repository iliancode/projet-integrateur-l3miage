package fr.uga.l3miage.example.repository;


import fr.uga.l3miage.example.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {


    Optional<Participant> findByPseudo(final String pseudo);
    int deleteByPseudo(final String pseudo);

    Optional<Participant> findById(final long id);
    int deleteById(final long id);

}
