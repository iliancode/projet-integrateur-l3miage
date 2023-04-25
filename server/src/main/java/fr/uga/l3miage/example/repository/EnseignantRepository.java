package fr.uga.l3miage.example.repository;


import fr.uga.l3miage.example.models.Enseignant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EnseignantRepository implements CRUDRepository<Long,Enseignant> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Enseignant save(Enseignant enseignant){
        entityManager.persist(enseignant);
        return enseignant;
    }

    @Override
    public Enseignant get(Long id) {
        return entityManager.find(Enseignant.class, id);
    }

    @Override
    public void delete(Enseignant enseignant) {
        entityManager.remove(enseignant);
    }

    @Override
    public List<Enseignant> all() {
        return entityManager.createQuery("SELECT e FROM Enseignant e ORDER BY id", Enseignant.class).getResultList();
    }
}
