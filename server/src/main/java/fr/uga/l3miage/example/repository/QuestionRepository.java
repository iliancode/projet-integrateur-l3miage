package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Question;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Long, Question> {

    Optional<Question> findByLabel(final String label);
    int deleteByLabel(final String label);

    Optional<Question> findById(final long id);
    int deleteById(final long id);

}
