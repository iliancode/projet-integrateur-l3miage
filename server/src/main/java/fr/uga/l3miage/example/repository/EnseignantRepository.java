package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {


    Optional<Enseignant> findByMail(final String mail);
    Optional<Enseignant> findById(final Long id);

    Optional<Enseignant> findByUid(final String uid);
    void deleteById(final Long id);

    //find all enseignants
    List<Enseignant> findAll();



}
