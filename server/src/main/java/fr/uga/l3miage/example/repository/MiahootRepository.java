package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Miahoot;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface MiahootRepository extends JpaRepository<Miahoot, Long> {



    int deleteById(final long aLong);


    List<Miahoot> findAll();

    <S extends Miahoot> S save(S s);
    Optional<Miahoot> findById(final Long id);
    void deleteById(final Long id);
}
