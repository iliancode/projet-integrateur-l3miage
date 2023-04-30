package fr.uga.l3miage.example.component;

//import fr.uga.l3miage.example.mapper.EnseignantMapper;

import fr.uga.l3miage.example.exception.technical.DescriptionAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.IsNotTestException;
import fr.uga.l3miage.example.exception.technical.MultipleEntityHaveSameDescriptionException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import fr.uga.l3miage.example.response.EnseignantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnseignantComponent {
    private final EnseignantRepository enseignantRepository;
    private final EnseignantMapper enseignantMapper;

    /**
     * @param enseignant à créer en base de données
     */
    public void createEnseignant(final Enseignant enseignant) {
        enseignantRepository.save(enseignant);
    }

    public void deleteEnseignantById(final long id) throws Exception {
        int deleted = enseignantRepository.deleteById(id);
        if (deleted > 1)
            throw new Exception("Plusieurs entités ont le même mail alors que c'est impossible niveau métier !!");
        else if (deleted == 0)
            throw new Exception("L'entité à supprimer n'a pas été trouvée " +  id);

    }

    /**
     * @param mail de l'entité Enseingnant à récupérer
     * @return une {@link Enseignant} correspondant à un mail donné
     * @throws EnseignantEntityNotFoundException si aucune entité Enseignant n'est trouvée
     */
    public Enseignant getEnseignantByMail(final String mail) throws EnseignantEntityNotFoundException {
            return enseignantRepository.findByMail(mail)
                    .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("L'entité à supprimer n'a pas été trouvée pour le mail [%s]", mail), mail));
    }

    //get all enseignants
    public List<Enseignant> getAllEnseignants() throws Exception {
        return enseignantRepository.findAll();
    }
    public void deleteEnseignantByMail(final String mail) throws MultipleEntityHaveSameDescriptionException, TestEntityNotFoundException {
        int deleted = enseignantRepository.deleteByMail(mail);
        log.info("deleted : " + deleted);
        if (deleted > 1)
            throw new MultipleEntityHaveSameDescriptionException("Plusieurs entités ont le même mail alors que c'est impossible niveau métier !!");
        else if (deleted == 0)
            throw new TestEntityNotFoundException("L'entité à supprimer n'a pas été trouvée " ,  mail);

    }

    public void updateEnseignantByMail(final String lastMail , final EnseignantDTO enseignant) throws TestEntityNotFoundException, IsNotTestException, DescriptionAlreadyExistException{

        if(!lastMail.equals(enseignant.getMail()) && enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
            throw new DescriptionAlreadyExistException(String.format("Ce mail existe deja dans la base de données"), enseignant.getMail());
        }

        Enseignant actuelEnseignant = enseignantRepository.findByMail(lastMail)
                .orElseThrow(() -> new TestEntityNotFoundException( String.format("Aucune entité n'a été trouvé pour le mail [%s]", lastMail), lastMail));

        enseignantMapper.mergeEnseignantEntity(actuelEnseignant,enseignant);
        enseignantRepository.save(actuelEnseignant);
    }
}
