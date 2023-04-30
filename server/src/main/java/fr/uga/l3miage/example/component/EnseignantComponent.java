package fr.uga.l3miage.example.component;

//import fr.uga.l3miage.example.mapper.EnseignantMapper;

import fr.uga.l3miage.example.exception.technical.alreadyExistException.MailAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
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
    public void createEnseignant(final Enseignant enseignant) throws MailAlreadyExistException {
        if(enseignantRepository.findByMail(enseignant.getMail()).isPresent()){
            throw new MailAlreadyExistException(String.format("Ce mail existe deja dans la base de données"), enseignant.getMail());
        }
        enseignantRepository.save(enseignant);
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

    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    public void deleteEnseignantByMail(final String mail) throws EnseignantEntityNotFoundException {
        int deleted = enseignantRepository.deleteByMail(mail);
        if (deleted == 0) {
            throw new EnseignantEntityNotFoundException("L'entité à supprimer n'a pas été trouvée ", mail);
        }
    }

    public void updateEnseignantByMail(final String lastMail , final CreateEnseignantRequest request) throws EnseignantEntityNotFoundException, MailAlreadyExistException {

        if(!lastMail.equals(request.getMail()) && enseignantRepository.findByMail(request.getMail()).isPresent()){
            throw new MailAlreadyExistException(String.format("Ce mail existe deja dans la base de données"), request.getMail());
        }

        Enseignant actuelEnseignant = enseignantRepository.findByMail(lastMail)
                .orElseThrow(() -> new EnseignantEntityNotFoundException( String.format("Aucune entité n'a été trouvé pour le mail [%s]", lastMail), lastMail));

        enseignantMapper.mergeEnseignantEntity(actuelEnseignant,request);
        enseignantRepository.save(actuelEnseignant);
    }
}
