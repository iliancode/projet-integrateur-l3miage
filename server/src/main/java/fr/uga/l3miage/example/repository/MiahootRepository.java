package fr.uga.l3miage.example.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

@Repository
public class MiahootRepository implements CRUDRepository<Long, Miahoot>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Miahoot save(Miahoot miahoot){
        entityManager.persist(miahoot);
        return miahoot;
    }

    @Override
    public Miahoot get(Long id) {
        return entityManager.find(Miahoot.class, id);
    }

    @Override
    public void delete(Miahoot miahoot) {
        entityManager.remove(miahoot);
    }

    @Override
    public List<Miahoot> all() {
        return entityManager.createQuery("SELECT m FROM Miahoot m ORDER BY id", Miahoot.class).getResultList();
    }
}
