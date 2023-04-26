package fr.uga.l3miage.example.repository;


import fr.uga.l3miage.example.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {


    Optional<Participant> findByPseudo(final String pseudo);
    int deleteByPseudo(final String pseudo);

    Optional<Participant> findById(final Long id);
    void deleteById(final Long id);

}