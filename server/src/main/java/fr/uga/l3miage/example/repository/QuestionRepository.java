package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionRepository implements CRUDRepository<Long, Question> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Question save(Question question){
        entityManager.persist(question);
        return question;
    }

    @Override
    public Question get(Long id) {
        return entityManager.find(Question.class, id);
    }

    @Override
    public void delete(Question question) {
        entityManager.remove(question);
    }

    @Override
    public List<Question> all() {
        return entityManager.createQuery("SELECT q FROM Question q ORDER BY id", Question.class).getResultList();
    }
}
