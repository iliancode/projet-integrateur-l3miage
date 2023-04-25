package fr.uga.l3miage.example.repository;


import fr.uga.l3miage.example.models.Participant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ParticipantRepository implements CRUDRepository<Long, Participant> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Participant save(Participant participant) {
        entityManager.persist(participant);
        return participant;
    }

    @Override
    public Participant get(Long id) {
        return entityManager.find(Participant.class, id);
    }

    @Override
    public void delete(Participant participant) {
        entityManager.remove(participant);
    }

    @Override
    public List<Participant> all() {
        return entityManager.createQuery("SELECT p FROM Participant p ORDER BY id", Participant.class).getResultList();

    }
}
